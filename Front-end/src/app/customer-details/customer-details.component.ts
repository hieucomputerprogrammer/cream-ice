import { Customer } from './../services/models/customer.model';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerDataService } from './../services/data/customer-data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent implements OnInit {
  id: number;
  customer: Customer;

  constructor(
    private customerDataService: CustomerDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.customer = new Customer();
    this.customerDataService.retrieveCustomer(this.id).subscribe(data => this.customer = data);
  }

  saveCustomer() {
    this.customerDataService.updateCustomer(this.id, this.customer)
      .subscribe(
        data => {
          console.log(data);
          this.router.navigate(['customers']);
        }
      );
  }

}
