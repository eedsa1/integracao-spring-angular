import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { BookService } from '../service/book.service';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {
  books = [];
  name = 'in28minutes';

  constructor(private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private bookService: BookService) { }

  ngOnInit() {
    this.bookService.retrieveAllBooks(this.authenticationService.username).subscribe(books => {
      this.books = books;
    });
  }

  retrieveBooks() {
    this.bookService.retrieveAllBooks(this.authenticationService.username).subscribe(books => {
      this.books = books;
    });
  }

  addBook() {
    this.router.navigate([`/book/-1`]);
  }

  updateBook(id) {
    this.router.navigate([`/book/${id}`]);
  }

  deleteBook(id) {
    this.bookService.deleteBook(this.authenticationService.username, id).subscribe(() => {
      this.retrieveBooks();
    });
  }

}
