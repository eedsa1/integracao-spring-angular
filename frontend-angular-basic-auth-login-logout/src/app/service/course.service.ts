import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Course } from '../model/Course';

const headers = new HttpHeaders().set('content-type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  // BASE_PATH: 'http://localhost:8080';

  constructor(private http: HttpClient) {

  }

  retrieveAllCourses(name) {
    return this.http.get<[]>(`http://localhost:8080/instructors/${name}/courses`, { headers });
  }

  // eslint-disable-next-line
  retrieveCourse(name, id) {
    //console.log('executed service')
    return this.http.get<Course>(`http://localhost:8080/instructors/${name}/courses/${id}`);
  }

  // eslint-disable-next-line
  deleteCourse(name, id) {
    //console.log('executed service')
    return this.http.delete<Object>(`http://localhost:8080/instructors/${name}/courses/${id}`);
  }

  // eslint-disable-next-line
  updateCourse(name, id, course) {
    //console.log('executed service')
    return this.http.put<Object>(`http://localhost:8080/instructors/${name}/courses/${id}`, course);
  }

  // eslint-disable-next-line
  createCourse(name, course) {
    //console.log('executed service')
    return this.http.post<Object>(`http://localhost:8080/instructors/${name}/courses/`, course);
  }

}
