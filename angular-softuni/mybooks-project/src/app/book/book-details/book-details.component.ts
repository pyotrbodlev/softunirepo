import {Component, OnInit} from '@angular/core';
import {Book} from '../book.model';
import {ActivatedRoute} from '@angular/router';
import {BooksService} from '../../services/books/books.service';
import {LoaderService} from '../../shared/loader/loader.service';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.scss']
})
export class BookDetailsComponent implements OnInit {
  book: Book;

  constructor(private router: ActivatedRoute, private booksService: BooksService, private loader: LoaderService) {
  }

  ngOnInit(): void {
    const id = this.router.snapshot.params.id;
    this.loader.isLoading = true;

    this.booksService.getBook(id).subscribe(book => {
      this.loader.isLoading = false;
      this.book = book;
    });
  }

}
