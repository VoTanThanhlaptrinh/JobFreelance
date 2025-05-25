import {AfterViewInit, Component, OnInit} from '@angular/core';
import {JobServiceService} from '../service/job-service.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-apply-for-job',
  imports: [],
  templateUrl: './apply-for-job.component.html',
  standalone: true,
  styleUrl: './apply-for-job.component.css'
})
export class ApplyForJobComponent implements AfterViewInit, OnInit{
  constructor(private jobService: JobServiceService,
              private route: ActivatedRoute,
              private router: Router){}
  jobId:number =0;
  ngOnInit(): void {
     this.jobId = Number(this.route.snapshot.paramMap.get('id'));
      if(this.jobId === 0){
        this.router.navigate(['/'])
        return
      }
    }
  ngAfterViewInit(): void {
    const form = document.getElementById('cvForm') as HTMLFormElement;
    const successMessage = document.getElementById('successMessage') as HTMLElement;

    form.addEventListener('submit', (e: Event) => {
      e.preventDefault();

      const fullName = (form.querySelector('#fullName') as HTMLInputElement)?.value.trim();
      const email = (form.querySelector('#email') as HTMLInputElement)?.value.trim();
      const phone = (form.querySelector('#phone') as HTMLInputElement)?.value.trim();
      const desiredPosition = (form.querySelector('#desiredPosition') as HTMLInputElement)?.value.trim();
      const skills = (form.querySelector('#skills') as HTMLInputElement)?.value.trim();

      const isInfoFilled = fullName && email && phone && desiredPosition && skills;

      if (!isInfoFilled) {
        alert('Vui lòng nhập đầy đủ thông tin và tải lên file CV.');
        return;
      }

      const cvInput = form.querySelector('#uploadCVFile') as HTMLInputElement;
      const fileAttached = cvInput?.files && cvInput.files.length > 0;
      if (!fileAttached) {
        alert('filenull')
        return;

      }

      const file = cvInput.files![0]; // dấu ! khẳng định rằng files không null ở đây
      const allowedExtensions = ['pdf', 'doc', 'docx'];
      const fileExtension = file.name.split('.').pop()?.toLowerCase();

      if (!fileExtension || !allowedExtensions.includes(fileExtension)) {
        alert('Định dạng file không hợp lệ. Vui lòng tải lên file PDF, DOC hoặc DOCX.');
        cvInput.value = '';
        return;
      }
      const formData = new FormData();
      formData.append('jobId',this.jobId.toString());
      formData.append('fullName', fullName);
      formData.append('email', email);
      formData.append('phone', phone);
      formData.append('desiredPosition', desiredPosition);
      formData.append('skills', skills);
      // @ts-ignore
      formData.append('cvInput',cvInput.files?.[0]);
      this.jobService.postCV(formData).subscribe({
      next: (res:any) =>{
        console.log(res);
      },error: (err) => {
        console.log(err);
      }
    })

      // Nếu hợp lệ
      successMessage.style.display = 'block';
      form.reset();
      successMessage.scrollIntoView({ behavior: 'smooth' });
    });


  }


}
