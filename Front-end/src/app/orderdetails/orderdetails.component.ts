import { OrderDetailsDataService } from './../services/data/order-details-data.service';
import { OrderDetailsDataSource } from './orderdetails-data-source';
import { Router } from '@angular/router';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { tap } from 'rxjs/operators';

export class OrderDetails {
  public id: number;
  public quantity: number;
  public price: number;
  public notes: string;
}

@Component({
  selector: 'app-orderdetails',
  templateUrl: './orderdetails.component.html',
  styleUrls: ['./orderdetails.component.css']
})
export class OrderdetailsComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = [
    'quantity',
    'price',
    'notes',
    'update',
    'delete'
  ];
  orderDetailsDataSource: OrderDetailsDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  message: string;

  constructor(
    private orderDetailsDataService: OrderDetailsDataService,
    private router: Router
    ) { }

  ngAfterViewInit() {
    this.orderDetailsDataSource.counter$
    .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
    )
    .subscribe();

    this.paginator.page
    .pipe(
        tap(() => this.orderDetailsDataSource.loadOrderDetails(this.paginator.pageIndex, this.paginator.pageSize))
    )
    .subscribe();
  }

  ngOnInit(): void {
    this.orderDetailsDataSource = new OrderDetailsDataSource(this.orderDetailsDataService);
    this.orderDetailsDataSource.loadOrderDetails(0, 5);
  }

  refreshOrderDetails() {
    this.paginator.pageIndex = 0;
    this.paginator.pageSize = 5;
    this.orderDetailsDataSource.loadOrderDetails(0, 5);
  }

  deleteOrderDetails(id) {
    this.orderDetailsDataService.deleteOrderDetails(id).subscribe(
      response => {
        console.log(response);
        this.message = `Order Details with ID number ${id} successfully deleted!`;
        this.refreshOrderDetails();
      }
    );
  }

  updateOrderDetails(id) {
    console.log(`Update Order Details with ID number ${id}.`);
    this.router.navigate(['orderdetails', id]);
  }

  addOrderDetails() {
    this.router.navigate(['create-orderdetails']);
  }

}
