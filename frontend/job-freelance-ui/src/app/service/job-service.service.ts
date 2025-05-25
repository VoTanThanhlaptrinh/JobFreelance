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
  // 13.1.4 Trỏ đến phương thức getListJob(Page) ở lớp JobServiceService,
  // gửi HTTP repuest đến Backend GET /get/jobPost/{page} để lấy danh sách công việc đã đăng tuyển của nhà tuyển dụng
  getListJob(page: number): Observable<any> {
    // 13.1.13 Từ JobAPI (Backend) sẽ trả về ResponseEntity<Response> cho JobServiceService (Frontend) qua /get/jobPost/{page}
    return this.http.get(`http://localhost:8080/api/job/get/jobPost/${page}`);
  }

}
