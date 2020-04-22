import { BasicAuthenticationService } from './../basic-authentication.service';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorBasicAuthService implements HttpInterceptor {

  constructor(private basicAuthenticationService: BasicAuthenticationService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler) {
    // let username = 'hieu';
    // let password = 'hieu';
    // let basicAuthenticationHeaderString = 'Basic ' + window.btoa(username + ':' + password);

    let basicAuthenticationHeaderString = this.basicAuthenticationService.getAuthenticatedToken();
    let username = this.basicAuthenticationService.getAuthenticatedUser();

    if (basicAuthenticationHeaderString && username) {
      request = request.clone({
        setHeaders: {
          Authorization: basicAuthenticationHeaderString
        }
      });
    }

    return next.handle(request);
  }
}
