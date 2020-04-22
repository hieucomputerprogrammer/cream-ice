import { Order } from './../models/order.model';
import { Observable } from 'rxjs';
import { API_URL } from './../../app.constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OrderDataService {

  constructor(private httpClient: HttpClient) { }

  retrieveAllOrders(orderSearchCriteria): Observable<any> {
    return this.httpClient.get(`${API_URL}/orders`, { params: orderSearchCriteria });
  }

  deleteOrder(id) {
    return this.httpClient.delete(`${API_URL}/orders/${id}`);
  }

  retrieveOrder(id) {
    return this.httpClient.get<Order>(`${API_URL}/orders/${id}`);
  }

  updateOrder(id, order) {
    return this.httpClient.put(`${API_URL}/orders/${id}`, order);
  }

  createOrder(order) {
    return this.httpClient.post(`${API_URL}/orders/`, order);
  }
}
