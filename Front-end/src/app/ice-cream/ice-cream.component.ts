import { tap } from 'rxjs/operators';
import { MatPaginator } from '@angular/material/paginator';
import { IceCreamDataSource } from './ice-cream-data-source';
import { Router } from '@angular/router';
import { IceCreamDataService } from './../services/data/ice-cream-data.service';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-ice-cream',
  templateUrl: './ice-cream.component.html',
  styleUrls: ['./ice-cream.component.css']
})
export class IceCreamComponent implements OnInit, AfterViewInit {
  // iceCreams: IceCream[];
  displayedColumns: string[] = [
    'name',
    'description',
    'update',
    'delete'
  ];
  iceCreamDataSource: IceCreamDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  message: string;

  constructor(
    private iceCreamDataService: IceCreamDataService,
    private router: Router
    ) { }

  ngAfterViewInit() {
    this.iceCreamDataSource.counter$
    .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
    )
    .subscribe();

    // when paginator event is invoked, retrieve the related data
    this.paginator.page
    .pipe(
        tap(() => this.iceCreamDataSource.loadIceCreams(this.paginator.pageIndex, this.paginator.pageSize))
    )
    .subscribe();
  }

  ngOnInit(): void {
    // this.refreshIceCreams();
    this.iceCreamDataSource = new IceCreamDataSource(this.iceCreamDataService);
    this.iceCreamDataSource.loadIceCreams(0, 5);
  }

  refreshIceCreams() {
    this.paginator.pageIndex = 0;
    this.paginator.pageSize = 5;
    this.iceCreamDataSource.loadIceCreams(0, 5);
  }

  deleteIceCream(id) {
    this.iceCreamDataService.deleteIceCream(id).subscribe(
      response => {
        console.log(response);
        this.message = `Ice-cream with ID number ${id} successfully deleted!`;
        this.refreshIceCreams();
      }
    );
  }

  updateIceCream(id) {
    console.log(`Update Ice-Cream with ID number ${id}.`);
    this.router.navigate(['ice-creams', id]);
  }

  addIceCream() {
    this.router.navigate(['create-ice-cream']);
  }

}
