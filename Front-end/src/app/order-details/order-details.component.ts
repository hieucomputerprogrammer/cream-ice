import { Order } from './../services/models/order.model';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderDataService } from './../services/data/order-data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {
  id: number;
  order: Order;

  constructor(
    private orderDataService: OrderDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.order = new Order();
    this.orderDataService.retrieveOrder(this.id).subscribe(data => this.order = data);
  }

  saveOrder() {
    this.orderDataService.updateOrder(this.id, this.order)
      .subscribe(
        data => {
          console.log(data);
          this.router.navigate(['orders']);
        }
      );
  }

}
