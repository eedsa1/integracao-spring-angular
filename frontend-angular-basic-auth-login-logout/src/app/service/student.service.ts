import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Student } from '../model/Student';

const headers = new HttpHeaders().set('content-type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  // BASE_PATH: 'http://localhost:8080';

  constructor(private http: HttpClient) {

  }

  retrieveAllStudents(name) {
    return this.http.get<[]>(`http://localhost:8080/instructors/${name}/students`, { headers });
  }

  // eslint-disable-next-line
  retrieveStudent(name, id) {
    //console.log('executed service')
    return this.http.get<Student>(`http://localhost:8080/instructors/${name}/students/${id}`);
  }

  // eslint-disable-next-line
  deleteStudent(name, id) {
    //console.log('executed service')
    return this.http.delete<Object>(`http://localhost:8080/instructors/${name}/students/${id}`);
  }

  // eslint-disable-next-line
  updateStudent(name, id, student) {
    //console.log('executed service')
    return this.http.put<Object>(`http://localhost:8080/instructors/${name}/students/${id}`, student);
  }

  // eslint-disable-next-line
  createStudent(name, student) {
    //console.log('executed service')
    return this.http.post<Object>(`http://localhost:8080/instructors/${name}/students/`, student);
  }

}
