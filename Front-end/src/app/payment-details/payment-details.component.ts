import { Payment } from './../services/models/payment.model';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentDataService } from './../services/data/payment-data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-payment-details',
  templateUrl: './payment-details.component.html',
  styleUrls: ['./payment-details.component.css']
})
export class PaymentDetailsComponent implements OnInit {
  id: number;
  payment: Payment;

  constructor(
    private paymentDataService: PaymentDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.payment = new Payment();
    this.paymentDataService.retrievePayment(this.id).subscribe(data => this.payment = data);
  }

  savePayment() {
    this.paymentDataService.updatePayment(this.id, this.payment)
      .subscribe(
        data => {
          console.log(data);
          this.router.navigate(['payments']);
        }
      );
  }

}
