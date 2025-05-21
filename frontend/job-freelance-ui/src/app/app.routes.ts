import { Routes } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {AboutComponent} from './about/about.component';
import {BlogComponent} from './blog/blog.component';
import {BlogSingleComponent} from './blog-single/blog-single.component';
import {ContactComponent} from './contact/contact.component';
import {FagComponent} from './fag/fag.component';
import {GalleryComponent} from './gallery/gallery.component';
import {JobListingsComponent} from './job-listings/job-listings.component';
import {JobSingleComponent} from './job-single/job-single.component';
import {LoginComponent} from './login/login.component';
import {PortfolioComponent} from './portfolio/portfolio.component';
import {PortfolioSingleComponent} from './portfolio-single/portfolio-single.component';
import {PostJobComponent} from './post-job/post-job.component';
import {ServiceSingleComponent} from './service-single/service-single.component';
import {ServicesComponent} from './services/services.component';
import {TestimonialsComponent} from './testimonials/testimonials.component';
import {ListJobComponent} from './list-job/list-job.component';

export const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'about', component: AboutComponent},
  {path: 'blog', component: BlogComponent},
  {path: 'blog-single', component: BlogSingleComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'fag', component: FagComponent},
  {path: 'gallery', component: GalleryComponent},
  {path: 'job-listings', component: JobListingsComponent},
  {path: 'job-single', component: JobSingleComponent},
  {path: 'login', component: LoginComponent},
  {path: 'portfolio', component: PortfolioComponent},
  {path: 'portfolio-single', component: PortfolioSingleComponent},
  {path: 'post-job', component: PostJobComponent},
  {path: 'service-single', component: ServiceSingleComponent},
  {path: 'services', component: ServicesComponent},
  {path: 'testimonials', component: TestimonialsComponent},
  {path: 'list-Job', component: ListJobComponent},
];
