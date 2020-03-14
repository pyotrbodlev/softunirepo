import {Component, OnInit} from '@angular/core';
import {Book} from '../book.model';
import {ActivatedRoute} from '@angular/router';
import {BooksService} from '../../services/books/books.service';
import {LoaderService} from '../../shared/loader/loader.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ReviewService} from '../../services/review/review.service';
import {Observable} from 'rxjs';
import {InfoSnackbarService} from "../../shared/snackbar/info-snackbar.service";
import {IReview} from "../review/review.model";

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.scss']
})
export class BookDetailsComponent implements OnInit {
  book: Book;
  reviews$: Observable<IReview[]>;
  reviewForm: FormGroup;

  constructor(private router: ActivatedRoute,
              private booksService: BooksService,
              private loader: LoaderService,
              private fb: FormBuilder,
              private reviewService: ReviewService,
              private snackBar: InfoSnackbarService) {

    this.reviewForm = fb.group({
      reviewField: new FormControl('', [Validators.required])
    });
  }

  ngOnInit(): void {
    const id = this.router.snapshot.params.id;
    this.loader.isLoading = true;

    this.booksService.getBook(id).subscribe(book => {
      this.loader.isLoading = false;
      this.book = book;
    });

    this.loadReviews();
  }

  loadReviews() {
    const bookId = this.router.snapshot.params.id;

    this.reviews$ = this.reviewService.loadReviews(bookId);
  }

  addReview() {
    const user = JSON.parse(sessionStorage.getItem('me'));
    const review = this.reviewForm.controls.reviewField.value;

    const body = {
      user: {
        username: user.username,
        _id: user._id,
        avatarUrl: user.avatarUrl
      },
      review,
      bookId: this.book._id
    };

    this.reviewService.addReview(body).subscribe(resp => {
      // @ts-ignore
      if (resp._id) {
        this.snackBar.openSnackBar('You added a review', 'Success');
        this.reviewForm.reset();
        this.loadReviews();
      }
    });
  }

}
