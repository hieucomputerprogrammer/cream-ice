import { BasicAuthenticationService } from './../services/basic-authentication.service';
import { HardcodedAuthenticationService } from './../services/hardcoded-authentication.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username = 'hieu';
  password = 'hieu';
  errorMessage = 'Invalid Credentials!';
  invalidLogin = false;

  constructor(
    private router: Router,
    private hardcodedAuthenticationService: HardcodedAuthenticationService,
    private basicAuthenticationService: BasicAuthenticationService
    ) { }

  ngOnInit(): void {
  }

  // login() {
  //   if (this.hardcodedAuthenticationService.authenticate(this.username, this.password)) {
  //     this.router.navigate(['welcome', this.username]);
  //     this.invalidLogin = false;
  //   } else {
  //     this.router.navigate(['login']);
  //     this.invalidLogin = true;
  //   }
  // }

  // handleBasicAuthenticationLogin() {
  //   this.basicAuthenticationService.executeAuthenticationService(this.username, this.password)
  //       .subscribe(
  //         data => {
  //           console.log(data);
  //           this.router.navigate(['welcome', this.username]);
  //           this.invalidLogin = false;
  //         },
  //         error => {
  //           console.log(error);
  //           this.invalidLogin = true;
  //         }
  //       );
  // }

  handleJWTAuthenticationLogin() {
    this.basicAuthenticationService.executeJWTAuthenticationService(this.username, this.password)
        .subscribe(
          data => {
            console.log(data);
            this.router.navigate(['products-list']);
            this.invalidLogin = false;
          },
          error => {
            console.log(error);
            this.invalidLogin = true;
          }
        );
  }

}
