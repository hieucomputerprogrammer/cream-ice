import { tap } from 'rxjs/operators';
import { MatPaginator } from '@angular/material/paginator';
import { OrderDataSource } from './order-data-source';
import { Router } from '@angular/router';
import { OrderDataService } from './../services/data/order-data.service';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit, AfterViewInit {
  // orders: Order[];
  displayedColumns: string[] = [
    'paymentOption',
    'createDate',
    'deliveryDetail',
    'notes',
    'status',
    'update',
    'delete'
  ];
  orderDataSource: OrderDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  message: string;

  constructor(
    private orderDataService: OrderDataService,
    private router: Router
    ) { }

  ngAfterViewInit() {
    this.orderDataSource.counter$
    .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
    )
    .subscribe();

    // when paginator event is invoked, retrieve the related data
    this.paginator.page
    .pipe(
        tap(() => this.orderDataSource.loadOrders(this.paginator.pageIndex, this.paginator.pageSize))
    )
    .subscribe();
  }

  ngOnInit(): void {
    this.orderDataSource = new OrderDataSource(this.orderDataService);
    this.orderDataSource.loadOrders(0, 5);
  }

  refreshOrders() {
    this.paginator.pageIndex = 0;
    this.paginator.pageSize = 5;
    this.orderDataSource.loadOrders(0, 5);
  }

  deleteOrder(id) {
    this.orderDataService.deleteOrder(id).subscribe(
      response => {
        console.log(response);
        this.message = `Order with ID number ${id} successfully deleted!`;
        this.refreshOrders();
      }
    );
  }

  updateOrder(id) {
    console.log(`Update Order with ID number ${id}.`);
    this.router.navigate(['orders', id]);
  }

  addOrder() {
    this.router.navigate(['create-order']);
  }

}
