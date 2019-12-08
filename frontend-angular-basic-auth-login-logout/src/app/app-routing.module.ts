import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { CoursesComponent } from './courses/courses.component';
import { CourseComponent } from './course/course.component';
import { ModulesComponent } from './modules/modules.component';
import { ModuleComponent } from './module/module.component';
import { StudentsComponent } from './students/students.component';
import { StudentComponent } from './student/student.component';
import { BooksComponent } from './books/books.component';
import { BookComponent } from './book/book.component';
import { LogoutComponent } from './logout/logout.component'; 
import { AuthGuardService} from './service/auth-guard.service';

const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'courses', component: CoursesComponent, canActivate: [AuthGuardService]},
  {path: 'course/:id', component: CourseComponent, canActivate: [AuthGuardService]},
  {path: 'modules', component: ModulesComponent, canActivate: [AuthGuardService]},
  {path: 'module/:id', component: ModuleComponent, canActivate: [AuthGuardService]},
  {path: 'students', component: StudentsComponent, canActivate: [AuthGuardService]},
  {path: 'student/:id', component: StudentComponent, canActivate: [AuthGuardService]},
  {path: 'books', component: BooksComponent, canActivate: [AuthGuardService]},
  {path: 'book/:id', component: BookComponent, canActivate: [AuthGuardService]},
  {path: 'logout', component: LogoutComponent,},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
