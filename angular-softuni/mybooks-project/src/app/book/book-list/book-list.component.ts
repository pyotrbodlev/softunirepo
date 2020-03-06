import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {BookShortInfo} from '../book.model';
import {BooksService} from '../../services/books/books.service';
import {LoaderService} from '../../shared/loader/loader.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements OnInit {
  books: BookShortInfo[];

  constructor(private router: Router, private booksService: BooksService, private loader: LoaderService) {
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

}
