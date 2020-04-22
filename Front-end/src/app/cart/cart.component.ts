import { CartDataService } from './../services/data/cart-data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItems = [];
  cart: any;
  userId = 2; // TODO: Implement userId from database.

  constructor(private cartDataService: CartDataService) { }

  ngOnInit(): void {
    const param = {
      userId: 0
    };
    this.cartDataService.retrieveCartItems(this.userId).subscribe(response => this.cart = response);
  }

  removeCartItem(productId) {
    this.cartDataService.removeCartItem(this.cart.orderId, productId).subscribe(response => this.cart = response);
  }

}
