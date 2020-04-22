import { Payment } from './../models/payment.model';
import { API_URL } from './../../app.constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PaymentDataService {

  constructor(private httpClient: HttpClient) { }

  retrieveAllPayments(paymentSearchCriteria) {
    return this.httpClient.get(`${API_URL}/payments`, { params: paymentSearchCriteria });
  }

  deletePayment(id) {
    return this.httpClient.delete(`${API_URL}/payments/${id}`);
  }

  retrievePayment(id) {
    return this.httpClient.get<Payment>(`${API_URL}/payments/${id}`);
  }

  updatePayment(id, payment) {
    return this.httpClient.put(`${API_URL}/payments/${id}`, payment);
  }

  createPayment(payment) {
    return this.httpClient.post(`${API_URL}/payments/`, payment);
  }
}
