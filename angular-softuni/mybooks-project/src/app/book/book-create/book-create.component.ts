import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {BooksService} from '../../services/books/books.service';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-book-create',
  templateUrl: './book-create.component.html',
  styleUrls: ['./book-create.component.scss']
})
export class BookCreateComponent implements OnInit {
  authors: any;

  createBookForm = new FormGroup({
    title: new FormControl(''),
    author: new FormControl(null),
    imageUrl: new FormControl(''),
    gender: new FormControl(''),
    description: new FormControl(''),
    likes: new FormControl(0)
  });

  constructor(private router: Router, private booksService: BooksService) {
  }

  get genders() {
    return ['Adventure', 'Fantasy', 'Thriller', 'Romantic', 'Child'];
  }

  ngOnInit(): void {
    if (!sessionStorage.getItem('authtoken')) {
      this.router.navigate(['/']);
    }

    this.booksService.getAuthors().subscribe(authors => this.authors = authors);
  }

  handleCreate() {
    this.booksService.createBook(this.createBookForm.value).subscribe(resp => {
      this.router.navigate(['/']);
    });
  }

}
