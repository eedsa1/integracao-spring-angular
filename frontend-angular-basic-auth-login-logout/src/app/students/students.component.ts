import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { StudentService } from '../service/student.service';

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {
  students = [];
  name = 'in28minutes';

  constructor(private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private studentService: StudentService) { }

  ngOnInit() {
    this.studentService.retrieveAllStudents(this.authenticationService.username).subscribe(students => {
      this.students = students;
    });
  }

  retrieveStudents() {
    this.studentService.retrieveAllStudents(this.authenticationService.username).subscribe(students => {
      this.students = students;
    });
  }

  addStudent() {
    this.router.navigate([`/student/-1`]);
  }

  updateStudent(id) {
    this.router.navigate([`/student/${id}`]);
  }

  deleteStudent(id) {
    this.studentService.deleteStudent(this.authenticationService.username, id).subscribe(() => {
      this.retrieveStudents();
    });
  }

}
