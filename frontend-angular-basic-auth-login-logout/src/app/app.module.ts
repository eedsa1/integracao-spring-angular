import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AuthenticationService } from './service/authentication.service';
import { CourseService } from './service/course.service';
import { ModuleService } from './service/module.service';
import { CoursesComponent } from './courses/courses.component';
import { HttpInterceptorService } from './service/httpInterceptor.service';
import { AuthGuardService } from './service/auth-guard.service';
import { LogoutComponent } from './logout/logout.component';
import { MenuComponent } from './menu/menu.component';
import { CourseComponent } from './course/course.component';
import { ModulesComponent } from './modules/modules.component';
import { ModuleComponent } from './module/module.component';
import { BooksComponent } from './books/books.component';
import { BookComponent } from './book/book.component';
import { StudentComponent } from './student/student.component';
import { StudentsComponent } from './students/students.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CoursesComponent,
    LogoutComponent,
    MenuComponent,
    CourseComponent,
    ModulesComponent,
    ModuleComponent,
    BooksComponent,
    BookComponent,
    StudentComponent,
    StudentsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [
    AuthenticationService,
    CourseService,
    ModuleService,
    AuthGuardService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
