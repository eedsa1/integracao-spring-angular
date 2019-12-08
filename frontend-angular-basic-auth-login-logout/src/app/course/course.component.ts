import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { CourseService } from '../service/course.service';
import { Course } from '../model/Course';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {

  id: number;
  errors = [];
  name = 'in28minutes';
  course: Course;

  constructor(private route: ActivatedRoute, 
    private router: Router,
    private authenticationService: AuthenticationService,
    private courseService: CourseService) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.course = new Course(this.id, '');
    if(this.id != -1) {
      this.courseService.retrieveCourse(this.authenticationService.username, this.id).subscribe((course) => {
        this.course = course;
      });
    }
  }

  saveSourse() {
    if(this.id != -1) {
      this.courseService.createCourse(this.authenticationService.username, this.course).subscribe(() => {
        this.router.navigate(['courses']);
      })
    } else {
      this.courseService.updateCourse(this.authenticationService.username, this.id, this.course).subscribe(() => {
        this.router.navigate(['courses']);
      })
    }
  }



}
