import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { CourseService } from '../service/course.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {
  courses = [];
  name = 'in28minutes';

  constructor(private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private courseService: CourseService) { }

  ngOnInit() {
    this.courseService.retrieveAllCourses(this.authenticationService.username).subscribe(courses => {
      this.courses = courses;
    });
  }

  retrieveCourses() {
    this.courseService.retrieveAllCourses(this.authenticationService.username).subscribe(courses => {
      this.courses = courses;
    });
  }

  addCourse() {
    this.router.navigate([`/course/-1`]);
  }

  updateCourse(id) {
    this.router.navigate([`/course/${id}`]);
  }

  deleteCourse(id) {
    this.courseService.deleteCourse(this.authenticationService.username, id).subscribe(() => {
      this.retrieveCourses();
    });
  }

}
