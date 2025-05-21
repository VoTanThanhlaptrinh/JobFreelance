import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JobServiceService {

  constructor(private http:HttpClient) { }

  doPostJob(form:any): Observable<any>{
    return this.http.post('http://localhost:8080/api/job/postJob',form);
  }

  getNDataJobNewest(number: number):Observable<any> {
    return this.http.get(`http://localhost:8080/api/job/get/newest/${number}`);
  }
  getListJob(number: number):Observable<any> {
    return this.http.get(`http://localhost:8080/api/job/get/jobPost/${number}`);
  }
}
