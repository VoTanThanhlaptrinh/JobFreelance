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
    file:File,
  }
  message: any;
  messageType: any;
  doPostJob(){
    const formData = new FormData();
    formData.append('title', this.job.title);
    formData.append('minSalary', this.job.minSalary);
    formData.append('maxSalary', this.job.maxSalary);
    formData.append('minDuration', this.job.minDuration);
    formData.append('maxDuration', this.job.maxDuration);
    formData.append('deadlineCV', this.job.deadlineCV); // dạng '2025-05-28'
    formData.append('description', this.job.description);
    formData.append('requirement', this.job.requirement);
    formData.append('skill', this.job.skill);
    if (this.job.file instanceof File) {
      formData.append('file', this.job.file);
    }
    /* 11.1.6: Component gọi doPostJob(), truyền dữ liệu form (formData) vào phương thức doPostJob() của jobService */
    this.jobService.doPostJob(formData).subscribe({
      next: (res:any) =>{
        /*11.1.14: trả về một response thông báo đăng thành công*/
        this.messageType = 'success'
        this.message = res.message;
        this.job = {
          title: '',
          minSalary:'',
          maxSalary: '',
          minDuration: '',
          maxDuration: '',
          deadlineCV:'',
          description:'',
          requirement:'',
          skill:'',
          file:''
        }
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
