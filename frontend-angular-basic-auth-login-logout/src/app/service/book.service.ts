import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from '../model/Book';

const headers = new HttpHeaders().set('content-type', 'application/json');

@Injectable({
  providedIn: 'root'
})
export class BookService {

  // BASE_PATH: 'http://localhost:8080';

  constructor(private http: HttpClient) {

  }

  retrieveAllBooks(name) {
    return this.http.get<[]>(`http://localhost:8080/instructors/${name}/books`);
  }

  // eslint-disable-next-line
  retrieveBook(name, id) {
    //console.log('executed service')
    return this.http.get<Book>(`http://localhost:8080/instructors/${name}/books/${id}`);
  }

  // eslint-disable-next-line
  deleteBook(name, id) {
    //console.log('executed service')
    return this.http.delete<Object>(`http://localhost:8080/instructors/${name}/books/${id}`);
  }

  // eslint-disable-next-line
  updateBook(name, id, book) {
    //console.log('executed service')
    return this.http.put<Object>(`http://localhost:8080/instructors/${name}/books/${id}`, book);
  }

  // eslint-disable-next-line
  createBook(name, book) {
    //console.log('executed service')
    return this.http.post<Object>(`http://localhost:8080/instructors/${name}/books/`, book);
  }

}
