import { Feedback } from './../models/feedback.model';
import { Observable } from 'rxjs';
import { API_URL } from './../../app.constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FeedbackDataService {

  constructor(private httpClient: HttpClient) { }

  retrieveAllFeedbacks(feedbackSearchCriteria): Observable<any> {
    return this.httpClient.get<Feedback[]>(`${API_URL}/feedbacks`, { params: feedbackSearchCriteria });
  }

  deleteFeedback(id) {
    return this.httpClient.delete(`${API_URL}/feedbacks/${id}`);
  }

  retrieveFeedback(id) {
    return this.httpClient.get<Feedback>(`${API_URL}/feedbacks/${id}`);
  }

  updateFeedback(id, feedback) {
    return this.httpClient.put(`${API_URL}/feedbacks/${id}`, feedback);
  }

  createFeedback(feedback) {
    return this.httpClient.post(`${API_URL}/feedbacks`, feedback);
  }
}
