import { CustomerDataSource } from './customer-data-source';
import { Router } from '@angular/router';
import { CustomerDataService } from './../services/data/customer-data.service';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit, AfterViewInit {
  // customers: Customer[];
  displayedColumns: string[] = [
    'name',
    'email',
    'phone',
    'dateOfBirth',
    'address',
    'gender',
    'avatar',
    'status',
    'numOfLoginFailed',
    'update',
    'delete'
  ];
  customerDataSource: CustomerDataSource;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  message: string;

  constructor(
    private customerDataService: CustomerDataService,
    private router: Router
    ) { }

  ngAfterViewInit() {
    this.customerDataSource.counter$
    .pipe(
        tap((count) => {
          this.paginator.length = count;
        })
    )
    .subscribe();

    // when paginator event is invoked, retrieve the related data
    this.paginator.page
    .pipe(
        tap(() => this.customerDataSource.loadCustomers(this.paginator.pageIndex, this.paginator.pageSize))
    )
    .subscribe();
  }

  ngOnInit(): void {
    this.customerDataSource = new CustomerDataSource(this.customerDataService);
    this.customerDataSource.loadCustomers(0, 5);
  }

  refreshCustomers() {
    // this.customerDataService.retrieveAllCustomers({}).subscribe(response => {
    //   console.log(response);
    //   // this.customers = response.content;
    // });
    this.paginator.pageIndex = 0;
    this.paginator.pageSize = 5;
    this.customerDataSource.loadCustomers(0, 5);
  }

  deleteCustomer(id) {
    this.customerDataService.deleteCustomer(id).subscribe(
      response => {
        console.log(response);
        this.message = `Customer with ID number ${id} successfully deleted!`;
        this.refreshCustomers();
      }
    );
  }

  updateCustomer(id) {
    console.log(`Update Customer with ID number ${id}.`);
    this.router.navigate(['customers', id]);
  }

  addCustomer() {
    this.router.navigate(['create-customer']);
  }

}
