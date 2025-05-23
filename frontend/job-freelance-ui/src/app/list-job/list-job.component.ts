import { Component, OnInit } from '@angular/core';
import { JobServiceService } from '../service/job-service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-list-job',
  templateUrl: './list-job.component.html',
  styleUrls: ['./list-job.component.css'],
  standalone: true,
  imports: [CommonModule]
})
export class ListJobComponent implements OnInit {

  listJob: any[] = [];
  message: string = '';
  initPage: number = 0;
  totalPages: number = 0;

  constructor(private jobService: JobServiceService) {}

  ngOnInit(): void {
    this.getListJob();
  }

  getListJob(): void {
    this.jobService.getListJob(this.initPage).subscribe({
      next: (res) => {
        this.message = res.message;

        const data = res.data;

        if (data && data.jobs) {
          this.listJob = data.jobs;
          this.totalPages = data.totalPages;
        } else {
          this.listJob = [];
          this.totalPages = 0;
        }
      },
      error: (error) => {
        this.message = 'Lỗi khi tải danh sách công việc.';
        this.listJob = [];
        this.totalPages = 0;
      }
    });
  }

  goToPage(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.initPage = page;
      this.getListJob();
    }
  }

  trackByJobId(index: number, item: any): number {
    return item.id;
  }
}
