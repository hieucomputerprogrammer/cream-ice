import { Payment } from './../services/models/payment.model';
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentDataService } from './../services/data/payment-data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-payment-create',
  templateUrl: './payment-create.component.html',
  styleUrls: ['./payment-create.component.css']
})
export class PaymentCreateComponent implements OnInit {
  id: number;
  payment: Payment;

  constructor(
    private paymentDataService: PaymentDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.payment = new Payment();
  }

  addPayment() {
    this.paymentDataService.createPayment(this.payment)
    .subscribe(
      data => {
        console.log(data);
        this.router.navigate(['payments']);
      }
    );
  }

}
