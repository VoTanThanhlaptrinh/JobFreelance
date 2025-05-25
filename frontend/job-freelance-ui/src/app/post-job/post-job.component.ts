import { Component } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {NgClass, NgIf} from '@angular/common';
import {JobServiceService} from '../service/job-service.service';

@Component({
  selector: 'app-post-job',
  imports: [FormsModule, NgIf, NgClass],
  templateUrl: './post-job.component.html',
  styleUrl: './post-job.component.css'
})
export class PostJobComponent {
  constructor(private jobService: JobServiceService) {
  }
  job:any = {
    title: '',
    minSalary:'',
    maxSalary: '',
    minDuration: '',
    maxDuration: '',
    deadlineCV:'',
    description:'',
    requirement:'',
    skill:'',
  }
  message: any;
  messageType: any;
  doPostJob(){
    /* 11.1.6: Component gọi doPostJob(), truyền dữ liệu form (this.job) vào phương thức doPostJob() của jobService */
    this.jobService.doPostJob(this.job).subscribe({
      next: (res:any) =>{
        /*11.1.14: trả về một response thông báo đăng thành công*/
        this.messageType = 'success'
        this.message = res.message;
      },error: (err) => {
        /*11.4.2: Hiển thị thông báo dữ liệu không hợp lệ.*/
        this.messageType= 'error';
        this.message = err.message;
        console.log(err);
      }
    })
  }
  validDate():boolean{
    let seletedDate = new Date(this.job.deadlineCV);
    return seletedDate > new Date();
  }
  validSalary():boolean{
    return parseFloat(this.job.minSalary) < parseFloat(this.job.maxSalary);
  }
  validDuration():boolean{
    return parseInt(this.job.minDuration) < parseInt(this.job.maxDuration);
  }
}
