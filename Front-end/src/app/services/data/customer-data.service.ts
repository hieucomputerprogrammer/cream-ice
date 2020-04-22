import { Customer } from './../models/customer.model';
import { API_URL } from './../../app.constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerDataService {

  constructor(private httpClient: HttpClient) { }

  retrieveAllCustomers(customerSearchCriteria): Observable<any> {
    return this.httpClient.get(`${API_URL}/customers`, { params: customerSearchCriteria });
  }

  deleteCustomer(id) {
    return this.httpClient.delete(`${API_URL}/customers/${id}`);
  }

  retrieveCustomer(id) {
    return this.httpClient.get<Customer>(`${API_URL}/customers/${id}`);
  }

  updateCustomer(id, customer) {
    return this.httpClient.put(`${API_URL}/customers/${id}`, customer);
  }

  createCustomer(customer) {
    return this.httpClient.post(`${API_URL}/customers`, customer);
  }
}
