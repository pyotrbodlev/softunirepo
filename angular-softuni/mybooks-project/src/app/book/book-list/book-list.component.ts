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
    if (!sessionStorage.getItem('authtoken')) {
      this.router.navigate(['/']);
    } else {
      this.loader.isLoading = true;
      this.booksService.loadBooks().subscribe(books => {
        this.loader.isLoading = false;
        this.books = books;
      });
    }
  }

  handleLike(book) {
    if (sessionStorage.getItem('authtoken')) {
      this.userService.likeBook(book.id).subscribe(resp => {
        sessionStorage.setItem('me', JSON.stringify(resp));
      });

      this.booksService.addLikes(book).subscribe();
    }
  }

  handleUnlike(book) {
    if (sessionStorage.getItem('authtoken')) {
      this.userService.unlikeBook(book.id).subscribe(resp => {
        sessionStorage.setItem('me', JSON.stringify(resp));
      });

      this.booksService.removeLikes(book).subscribe();
    }
  }

}
