import { tap } from 'rxjs/operators';
import { MatPaginator } from '@angular/material/paginator';
import { PaymentDataSource } from './payment-data-source';
import { Router } from '@angular/router';
import { PaymentDataService } from './../services/data/payment-data.service';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit, AfterViewInit {
  // payments: Payment[];
  displayedColumns: string[] = [
    'cardType',
    'cvv',
    'nameOnCard',
    'expiredDate',
    'dateOfBirth',
    'update',
    'delete'
  ];
  paymentDataSource: PaymentDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  message: string;

  constructor(
    private paymentDataService: PaymentDataService,
    private router: Router
    ) { }

  ngAfterViewInit() {
    this.paymentDataSource.counter$
    .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
    )
    .subscribe();

    // when paginator event is invoked, retrieve the related data
    this.paginator.page
    .pipe(
        tap(() => this.paymentDataSource.loadPayments(this.paginator.pageIndex, this.paginator.pageSize))
    )
    .subscribe();
  }

  ngOnInit(): void {
    this.paymentDataSource = new PaymentDataSource(this.paymentDataService);
    this.paymentDataSource.loadPayments(0, 5);
  }

  refreshPayments() {
    this.paginator.pageIndex = 0;
    this.paginator.pageSize = 5;
    this.paymentDataSource.loadPayments(0, 5);
  }

  deletePayment(id) {
    this.paymentDataService.deletePayment(id).subscribe(
      response => {
        console.log(response);
        this.message = `Payment with ID number ${id} successfully deleted!`;
        this.refreshPayments();
      }
    );
  }

  updatePayment(id) {
    console.log(`Update Payment with ID number ${id}.`);
    this.router.navigate(['payments', id]);
  }

  addPayment() {
    this.router.navigate(['create-payment']);
  }

}
