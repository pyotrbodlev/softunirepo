import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Book} from '../book.model';
import {BooksService} from '../../services/books/books.service';
import {LoaderService} from '../../shared/loader/loader.service';
import {UserService} from '../../services/user/user.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements OnInit {
  books: Book[];

  constructor(
    private router: Router,
    private booksService: BooksService,
    private loader: LoaderService,
    private userService: UserService) {
  }

  ngOnInit(): void {
    this.loader.isLoading = true;
    this.booksService.getBooks().subscribe(books => {
      this.loader.isLoading = false;
      this.books = books;
    });
  }

  handleLike(book) {
    if (sessionStorage.getItem('authtoken')) {
      this.userService.likeBook(book._id).subscribe(resp => {
        sessionStorage.setItem('me', JSON.stringify(resp));
      });

      this.booksService.addLikes(book).subscribe();
    }
  }

  handleUnlike(book) {
    if (sessionStorage.getItem('authtoken')) {
      this.userService.unlikeBook(book._id).subscribe(resp => {
        sessionStorage.setItem('me', JSON.stringify(resp));
      });

      this.booksService.removeLikes(book).subscribe();
    }
  }

}
