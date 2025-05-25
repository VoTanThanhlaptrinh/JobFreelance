import {AfterViewInit, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {RouterLink, RouterLinkActive} from '@angular/router';
import {NgIf} from '@angular/common';
import {AuthService} from '../service/auth-service.service';

@Component({
  selector: 'app-header',
  imports: [
    RouterLink,
    RouterLinkActive
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements AfterViewInit{
  isLogin: boolean = false; // ban đầu chưa login

  constructor(private authService: AuthService,private cdr: ChangeDetectorRef) {}

  ngAfterViewInit(): void {
    const token = localStorage.getItem('token');
    if (token) {
      this.authService.isLogin(token).subscribe({
        next:(res:any) => {
          this.isLogin = (res.status === 200);
        }
        , error:(error:any) => {
          this.isLogin = false;
        }});
    } else {
      this.isLogin = false;}
    }
  doLogout(){
    window.location.reload()
    localStorage.removeItem('token');
  }
  doListJob(){
    window.location.replace("/list-Job")
  }
}
