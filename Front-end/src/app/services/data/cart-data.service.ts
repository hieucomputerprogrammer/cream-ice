import { Observable } from 'rxjs';
import { API_URL } from './../../app.constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartDataService {

  constructor(private httpClient: HttpClient) { }

  retrieveCartItems(userId): Observable<any> {
    const param = {
      userId
    };
    return this.httpClient.get(`${API_URL}/cart`, { params: param });
  }

  addItemToCart(cartItem): Observable<any> {
    return this.httpClient.post(`${API_URL}/cart`, cartItem);
  }

  removeCartItem(orderId, productId) {
    const params = {
      orderId,
      productId
    };
    return this.httpClient.delete(`${API_URL}/cart`, { params: params });
  }
}
