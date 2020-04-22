import { Role } from './../models/role.model';
import { API_URL } from './../../app.constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RoleDataService {
  roles: Role[];

  constructor(private httpClient: HttpClient) { }

  retrieveAllRoles() {
    return this.httpClient.get(`${API_URL}/roles`);
  }
}
