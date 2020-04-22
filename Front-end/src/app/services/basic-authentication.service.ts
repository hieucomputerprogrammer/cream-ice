import { API_URL } from './../app.constants';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

export const TOKEN = 'token';
export const AUTHENTICATED_USER = 'authenticatedUser';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthenticationService {

  constructor(private httpClient: HttpClient) { }

  executeJWTAuthenticationService(username, password) {
    return this.httpClient.post<any>(`${API_URL}/authenticate`, {
      username,
      password
    }).pipe(
      map(
        data => {
          sessionStorage.setItem(AUTHENTICATED_USER, username);
          sessionStorage.setItem(TOKEN, `Bearer ${data.token}`);
          return data;
        }
      )
    );
  }

  // authenticate(username, password) {
  //   if (username === 'hieu' && password === 'hieu.minhle@nashtechglobal.com') {
  //     sessionStorage.setItem('authenticatedUser', username);
  //     return true;
  //   } else {
  //     return false;
  //   }
  // }

  // executeAuthenticationService(username, password) {
  //   let basicAuthenticationHeaderString = 'Basic ' + window.btoa(username + ':' + password);
  //   let headers = new HttpHeaders({
  //     Authorization: basicAuthenticationHeaderString
  //   });

  //   return this.httpClient.get<IceCream>(`${API_URL}/iceCreams`, {headers}).pipe(
  //     map(
  //       data => {
  //         sessionStorage.setItem('authenticatedUser', username);
  //         sessionStorage.setItem('token', basicAuthenticationHeaderString);
  //         return data;
  //       }
  //     )
  //   );
  // }

  getAuthenticatedUser() {
    return sessionStorage.getItem('authenticatedUser');
  }

  getAuthenticatedToken() {
    if (this.getAuthenticatedUser()) {
      return sessionStorage.getItem('token');
    }
  }

  isUserLoggedIn() {
    const user = sessionStorage.getItem('authenticatedUser');
    return !(user === null);
  }

  logout() {
    sessionStorage.removeItem('authenticatedUser');
    sessionStorage.removeItem('authenticatedUser');
  }
}
