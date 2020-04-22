import { tap } from 'rxjs/operators';
import { MatPaginator } from '@angular/material/paginator';
import { FAQDataSource } from './faq-data-source';
import { Router } from '@angular/router';
import { FaqDataService } from './../services/data/faq-data.service';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.css']
})
export class FaqComponent implements OnInit, AfterViewInit {
  // faqs: FAQ[];
  displayedColumns: string[] = [
    'question',
    'answer',
    'status',
    'update',
    'delete'
  ];
  faqDataSource: FAQDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  message: string;

  constructor(
    private faqDataService: FaqDataService,
    private router: Router
    ) { }

  ngAfterViewInit() {
    this.faqDataSource.counter$
    .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
    )
    .subscribe();

    // when paginator event is invoked, retrieve the related data
    this.paginator.page
    .pipe(
        tap(() => this.faqDataSource.loadFAQs(this.paginator.pageIndex, this.paginator.pageSize))
    )
    .subscribe();
  }

  ngOnInit(): void {
    // this.refreshFAQs();
    this.faqDataSource = new FAQDataSource(this.faqDataService);
    this.faqDataSource.loadFAQs(0, 5);
  }

  refreshFAQs() {
    // this.faqDataService.retrieveAllFAQs().subscribe(response => {
    //   console.log(response);
    //   this.faqs = response.content;
    // });
    this.paginator.pageIndex = 0;
    this.paginator.pageSize = 5;
    this.faqDataSource.loadFAQs(0, 5);
  }

  deleteFAQ(id) {
    this.faqDataService.deleteFAQ(id).subscribe(
      response => {
        console.log(response);
        this.message = `FAQ with ID number ${id} successfully deleted!`;
        this.refreshFAQs();
      }
    );
  }

  updateFAQ(id) {
    console.log(`Update FAQ with ID number ${id}.`);
    this.router.navigate(['faqs', id]);
  }

  addFAQ() {
    this.router.navigate(['create-faq']);
  }

}
