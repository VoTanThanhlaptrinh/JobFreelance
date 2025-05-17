import {Component, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {AuthService} from '../service/auth-service.service';
import {Router} from '@angular/router';
import {NgClass, NgIf} from '@angular/common';
import {delay} from 'rxjs';

@Component({
  selector: 'app-login',
  imports: [FormsModule, NgIf, NgClass],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{
  messageRegister: any = ''
  messageTypeRegister: any = ''
  messageLogin: any = ''
  messageTypeLogin: any = ''
  loginObj: any ={
    username: '',
    password: ''
  }
  register: any ={
    username: '',
    password: '',
    rePassword: ''
}
  constructor(private authService: AuthService
              , private router: Router) {
  }

  ngOnInit(): void {
    const token = localStorage.getItem('token');
    if (token) {
      this.authService.isLogin(token).subscribe({
        next:(res:any) => {
            this.router.navigate(['/'])
        }});
    }
  }
  doLogin(){
    const formValue = this.loginObj;
    this.authService.doLogin(formValue).subscribe({
      next: (res: any) => {
        this.messageTypeLogin= 'success';
        this.messageLogin = res.message;
        localStorage.setItem('token',res.data);
        setTimeout(() =>{
          window.location.reload();
          this.router.navigate(['/']);
        },2000)
      },
      error: (err) => {
        console.error('Error from backend:', err);
        this.messageTypeLogin= 'error';
        this.messageLogin = err.error.message;
      }
    });
  }
  doRegister(){
    const formValue = this.register;
    this.authService.doRegister(formValue).subscribe((res:any) =>{
      this.messageTypeRegister= 'success';
      this.messageRegister = res.message
    },(error) =>{
      console.log(error)
      this.messageTypeRegister = 'error';
      this.messageRegister = error.message
    })
  }
  isMatchPass():boolean{
    return this.register.password === this.register.rePassword
  }
}
