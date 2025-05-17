import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {observableToBeFn} from 'rxjs/internal/testing/TestScheduler';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }
  doLogin(form:any){
    return this.http.post('http://localhost:8080/api/account/login',form)
  }
  doRegister(form:any){
     return this.http.post('http://localhost:8080/api/account/register',form)
  }

  isLogin(token:any) {
    return this.http.get(`http://localhost:8080/api/account/isLogin/${token}`);
  }
}
