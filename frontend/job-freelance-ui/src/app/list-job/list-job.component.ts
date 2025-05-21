import {Component, OnInit} from '@angular/core';
import {JobServiceService} from '../service/job-service.service';
import {error} from 'jquery';

@Component({
  selector: 'app-list-job',
  imports: [],
  templateUrl: './list-job.component.html',
  styleUrl: './list-job.component.css'
})
export class ListJobComponent implements OnInit{
  constructor(private jobService: JobServiceService ) {
  }
  listJob: any = [];
  initPage:number = 0;
  getListJob() {
    this.jobService.getListJob(this.initPage).subscribe({
      next: (res) =>{
        this.listJob = res.data.map((item: any) => ({
          id : item.id,
          title: item.title,
          rangeSalary: item.rangeSalary,
          deadlineCV: item.deadlineCV,
        }));
        console.log(res.data.content);
      },
      error: (error) =>{
        console.log(error.message)
      }
    })
  }

  ngOnInit(): void {
    this.getListJob();
  }
}
