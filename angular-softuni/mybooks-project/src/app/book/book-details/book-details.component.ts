import {Component, OnInit} from '@angular/core';
import {Book} from '../book.model';
import {ActivatedRoute} from '@angular/router';
import {BooksService} from '../../services/books/books.service';
import {LoaderService} from '../../shared/loader/loader.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ReviewService} from '../../services/review/review.service';
import {map, shareReplay} from 'rxjs/operators';
import {Observable} from 'rxjs';

interface IReview {
  user: {
    username: string,
    _id: string
  };
  review: string;
}

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
              private reviewService: ReviewService) {

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

    this.reviews$ = this.reviewService.loadReviews(bookId).pipe(
      map(reviews => reviews.length > 0 ? reviews : null),
      shareReplay(1)
    );
  }

  addReview() {
    const user = JSON.parse(sessionStorage.getItem('me'));
    const review = this.reviewForm.controls.reviewField.value;

    const body = {
      user: {
        username: user.username,
        id: user._id
      },
      review,
      bookId: this.book._id
    };

    this.reviewService.addReview(body).subscribe(resp => {
      // @ts-ignore
      if (resp._id) {
        this.reviewForm.reset();
        this.loadReviews();
      }
    });
  }

}
