import { tap } from 'rxjs/operators';
import { MatPaginator } from '@angular/material/paginator';
import { FeedbackDataSource } from './feedback-data-source';
import { Router } from '@angular/router';
import { FeedbackDataService } from './../services/data/feedback-data.service';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-feedbacks',
  templateUrl: './feedbacks.component.html',
  styleUrls: ['./feedbacks.component.css']
})
export class FeedbacksComponent implements OnInit, AfterViewInit {
  // feedbacks: Feedback[];
  displayedColumns: string[] = [
    'details',
    'createDate',
    'update',
    'delete'
  ];
  feedbackDataSource: FeedbackDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  message: string;

  constructor(
    private feedbackDataService: FeedbackDataService,
    private router: Router
    ) { }

  ngAfterViewInit() {
    this.feedbackDataSource.counter$
    .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
    )
    .subscribe();

    // when paginator event is invoked, retrieve the related data
    this.paginator.page
    .pipe(
        tap(() => this.feedbackDataSource.loadFeedbacks(this.paginator.pageIndex, this.paginator.pageSize))
    )
    .subscribe();
  }

  ngOnInit(): void {
    // this.refreshFeedbacks();
    this.feedbackDataSource = new FeedbackDataSource(this.feedbackDataService);
    this.feedbackDataSource.loadFeedbacks(0, 5);
  }

  refreshFeedbacks() {
    // this.feedbackDataService.retrieveAllFeedbacks().subscribe(response => {
    //   this.feedbacks = response;
    // });
    this.paginator.pageIndex = 0;
    this.paginator.pageSize = 5;
    this.feedbackDataSource.loadFeedbacks(0, 5);
  }

  deleteFeedback(id) {
    this.feedbackDataService.deleteFeedback(id).subscribe(
      response => {
        console.log(response);
        this.message = `Feedback with ID number ${id} successfully deleted!`;
        this.refreshFeedbacks();
      }
    );
  }

  updateFeedback(id) {
    console.log(`Update Feedback with ID number ${id}.`);
    this.router.navigate(['feedbacks', id]);
  }

  addFeedback() {
    this.router.navigate(['create-feedback']);
  }

}
