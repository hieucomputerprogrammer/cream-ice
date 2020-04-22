import { Order } from './../services/models/order.model';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderDataService } from './../services/data/order-data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-order-create',
  templateUrl: './order-create.component.html',
  styleUrls: ['./order-create.component.css']
})
export class OrderCreateComponent implements OnInit {
  id: number;
  order: Order;

  constructor(
    private orderDataService: OrderDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.order = new Order();
  }

  addOrder() {
    this.orderDataService.createOrder(this.order)
    .subscribe(
      data => {
        console.log(data);
        this.router.navigate(['orders']);
      }
    );
  }

}
