import { Observable } from 'rxjs';
import { API_URL } from './../../app.constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

export class OrderDetails {
  public id: number;
  public quantity: number;
  public price: number;
  public notes: string;
}

@Injectable({
  providedIn: 'root'
})
export class OrderDetailsDataService {

  constructor(private httpClient: HttpClient) { }

  retrieveAllOrderDetails(orderDetailsSearchCriteria): Observable<any> {
    return this.httpClient.get(`${API_URL}/orderDetails`, { params: orderDetailsSearchCriteria });
  }

  deleteOrderDetails(id) {
    return this.httpClient.delete(`${API_URL}/orderDetails/${id}`);
  }

  retrieveOrderDetails(id) {
    return this.httpClient.get<OrderDetails>(`${API_URL}/orderDetails/${id}`);
  }

  updateOrderDetails(id, order) {
    return this.httpClient.put(`${API_URL}/orderDetails/${id}`, order);
  }

  createOrderDetails(order) {
    return this.httpClient.post(`${API_URL}/orderDetails/`, order);
  }
}
