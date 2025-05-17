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
    email: '',
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
    this.jobService.doPostJob(this.job).subscribe({
      next: (res:any) =>{
        this.messageType = 'success'
        this.message = res.message;
      },error: (err) => {
        this.messageType= 'error';
        this.message = err.message;
      }
    })
  }
}
