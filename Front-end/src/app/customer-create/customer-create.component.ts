import { Customer } from './../services/models/customer.model';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerDataService } from './../services/data/customer-data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-customer-create',
  templateUrl: './customer-create.component.html',
  styleUrls: ['./customer-create.component.css']
})
export class CustomerCreateComponent implements OnInit {
  id: number;
  customer: Customer;

  constructor(
    private customerDataService: CustomerDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.customer = new Customer();
  }

  addCustomer() {
    this.customerDataService.createCustomer(this.customer)
    .subscribe(
      data => {
        console.log(data);
        this.router.navigate(['customers']);
      }
    );
  }

}
