import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { StudentService } from '../service/student.service';
import { Student } from '../model/Student';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {

  id: number;
  errors = [];
  name = 'in28minutes';
  student: Student;

  constructor(private route: ActivatedRoute, 
    private router: Router,
    private authenticationService: AuthenticationService,
    private studentService: StudentService) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.student = new Student(this.id, '', '', '');
    if(this.id != -1) {
      this.studentService.retrieveStudent(this.authenticationService.username, this.id).subscribe((student) => {
        this.student = student;
      });
    }
  }

  saveSourse() {
    if(this.id != -1) {
      this.studentService.createStudent(this.authenticationService.username, this.student).subscribe(() => {
        this.router.navigate(['students']);
      })
    } else {
      this.studentService.updateStudent(this.authenticationService.username, this.id, this.student).subscribe(() => {
        this.router.navigate(['students']);
      })
    }
  }



}
