import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user/user.service';
import {ActivatedRoute} from '@angular/router';
import {IUser} from '../../shared/IUser';
import {LoaderService} from '../../shared/loader/loader.service';
import {Book} from '../../book/book.model';
import {BooksService} from '../../services/books/books.service';
import {map, shareReplay} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {InfoSnackbarService} from '../../shared/snackbar/info-snackbar.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  userInfo: IUser;
  defaultAvatarUrl = 'https://i1.wp.com/quaan.one/wp-content/uploads/2018/08/default-avatar.jpg?ssl=1';
  likedBooks: Observable<Book[]>;
  addedBooks: Observable<Book[]>;

  constructor(private userService: UserService,
              private booksService: BooksService,
              private router: ActivatedRoute,
              private loader: LoaderService,
              private snackBar: InfoSnackbarService) {
  }

  ngOnInit(): void {
    this.loader.isLoading = true;
    const userId = this.router.snapshot.params.id;

    this.userService.getUserById(userId).subscribe(user => {
      this.loader.isLoading = false;
      this.userInfo = user;
    });

    this.loadBooks();
  }

  loadBooks() {
    this.likedBooks = this.booksService.getBooks().pipe(
      map(books => books.filter(book => book.isLiked)),
      map(books => books.length > 0 ? books : undefined),
      shareReplay(1)
    );
    this.addedBooks = this.booksService.getBooks().pipe(
      map(books => books.filter(book => book._acl.creator === this.userService.currentUser._id)),
      map(books => books.length > 0 ? books : undefined),
      shareReplay(1)
    );
  }

  handleDeleteBook(book: Book) {
    this.loader.isLoading = true;

    this.booksService.deleteBook(book._id).subscribe({
      next: () => this.handleSuccess(book.title),
      error: () => this.handleError()
    });
  }

  handleSuccess(bookTitle) {
    this.loader.isLoading = false;
    this.loadBooks();
    this.snackBar.openSnackBar(`${bookTitle} was successfully deleted.`, 'Success');
  }

  handleError() {
    this.loader.isLoading = false;
    this.loadBooks();
    this.snackBar.openSnackBar(`Something went wrong!`, 'Error');
  }
}
