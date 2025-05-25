import {Component, OnInit} from '@angular/core';
import {JobServiceService} from '../service/job-service.service';
import {ActivatedRoute, Router} from '@angular/router';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-job-single',
  imports: [
    NgIf
  ],
  templateUrl: './job-single.component.html',
  styleUrl: './job-single.component.css'
})

export class JobSingleComponent implements OnInit{
  job: any;

  constructor(
    private route: ActivatedRoute,
    private jobService: JobServiceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const jobId = Number(this.route.snapshot.paramMap.get('id'));
    this.jobService.getJob(jobId).subscribe({
      next: (res:any) => {
        this.job = res.data;

      },
      error: (err) => {
        console.error('Lỗi khi lấy job:', err);
      }
    });
  }
  goApply(id: number) {
    this.router.navigate(['/apply-for-job', id]);
  }
}
