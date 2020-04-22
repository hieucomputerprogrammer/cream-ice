import { FAQ } from './../models/faq.model';
import { Observable } from 'rxjs';
import { API_URL } from './../../app.constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FaqDataService {

  constructor(private httpClient: HttpClient) { }

  retrieveAllFAQs(faqSearchCriteria): Observable<any> {
    return this.httpClient.get(`${API_URL}/faqs`, { params: faqSearchCriteria });
  }

  deleteFAQ(id) {
    return this.httpClient.delete(`${API_URL}/faqs/${id}`);
  }

  retrieveFAQ(id) {
    return this.httpClient.get<FAQ>(`${API_URL}/faqs/${id}`);
  }

  updateFAQ(id, faq) {
    return this.httpClient.put(`${API_URL}/faqs/${id}`, faq);
  }

  createFAQ(faq) {
    return this.httpClient.post(`${API_URL}/faqs`, faq);
  }
}
