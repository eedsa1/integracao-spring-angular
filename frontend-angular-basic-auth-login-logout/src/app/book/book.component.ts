import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { BookService } from '../service/book.service';
import { Book } from '../model/Book';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  id: number;
  errors = [];
  name = 'in28minutes';
  book: Book;

  constructor(private route: ActivatedRoute, 
    private router: Router,
    private authenticationService: AuthenticationService,
    private bookService: BookService) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.book = new Book(this.id, '', '');
    if(this.id != -1) {
      this.bookService.retrieveBook(this.authenticationService.username, this.id).subscribe((book) => {
        this.book = book;
      });
    }
  }

  saveSourse() {
    if(this.id != -1) {
      this.bookService.createBook(this.authenticationService.username, this.book).subscribe(() => {
        this.router.navigate(['books']);
      })
    } else {
      this.bookService.updateBook(this.authenticationService.username, this.id, this.book).subscribe(() => {
        this.router.navigate(['books']);
      })
    }
  }



}
