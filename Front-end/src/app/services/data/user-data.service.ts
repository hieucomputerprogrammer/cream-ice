import { User } from './../models/user.model';
import { Role } from './../models/role.model';
import { Observable } from 'rxjs';
import { API_URL } from './../../app.constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {

  constructor(private httpClient: HttpClient) { }

  retrieveAllUsers(userSearchCriteria): Observable<any> {
    return this.httpClient.get(`${API_URL}/users`, { params: userSearchCriteria });
  }

  deleteUser(id) {
    return this.httpClient.delete(`${API_URL}/users/${id}`);
  }

  retrieveUser(id) {
    return this.httpClient.get<User>(`${API_URL}/users/${id}`);
  }

  updateUser(id, user) {
    return this.httpClient.put(`${API_URL}/users/${id}`, user);
  }

  createUser(user) {
    return this.httpClient.post(`${API_URL}/users`, user);
  }
}
