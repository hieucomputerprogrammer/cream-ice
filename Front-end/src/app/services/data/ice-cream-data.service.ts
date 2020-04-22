import { IceCream } from './../models/ice-cream.model';
import { Observable } from 'rxjs';
import { API_URL } from './../../app.constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class IceCreamDataService {

  constructor(private httpClient: HttpClient) { }

  retrieveAllIceCreams(iceCreamSearchCriteria): Observable<any> {
    return this.httpClient.get(`${API_URL}/ice-creams`, { params: iceCreamSearchCriteria });
  }

  deleteIceCream(id) {
    return this.httpClient.delete(`${API_URL}/ice-creams/${id}`);
  }

  retrieveIceCream(id) {
    return this.httpClient.get<IceCream>(`${API_URL}/ice-creams/${id}`);
  }

  updateIceCream(id, iceCream) {
    return this.httpClient.put(`${API_URL}/ice-creams/${id}`, iceCream);
  }

  createIceCream(iceCream) {
    return this.httpClient.post(`${API_URL}/ice-creams/`, iceCream);
  }
}
