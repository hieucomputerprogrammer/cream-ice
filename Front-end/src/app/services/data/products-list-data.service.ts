import { RecipeDataService } from './recipe-data.service';
import { API_URL } from '../../app.constants';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

export class IceCream {
  public id: number;
  public name: string;
  public description: string;
}

@Injectable({
  providedIn: 'root'
})
export class ProductsListDataService {

  constructor(private httpClient: HttpClient) { }

  getIceCreamsDataFromTheBackend() {
    // let basicAuthenticationHeaderString = this.createBasicAuthenticationHttpHeader();
    // let headers = new HttpHeaders({
    //   Authorization: basicAuthenticationHeaderString
    // });
    return this.httpClient.get<IceCream>(`${API_URL}/ice-creams` /* , {headers} */);
    // console.log('Execute HelloWorldBeanService!');
  }

  // createBasicAuthenticationHttpHeader() {
  //   let username = 'hieu';
  //   let password = 'hieu';
  //   let basicAuthenticationHeaderString = 'Basic ' + window.btoa(username + ':' + password);
  //   return basicAuthenticationHeaderString;
  // }
}
