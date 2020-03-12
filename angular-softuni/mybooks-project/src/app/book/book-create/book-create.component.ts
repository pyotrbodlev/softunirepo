import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {BooksService} from '../../services/books/books.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-book-create',
  templateUrl: './book-create.component.html',
  styleUrls: ['./book-create.component.scss']
})
export class BookCreateComponent implements OnInit {
  authors$: Observable<any>;
  selectAuthor = true;

  createBookForm = new FormGroup({
    title: new FormControl('', [Validators.required]),
    author: new FormControl(null, [Validators.required]),
    imageUrl: new FormControl('', [Validators.required, Validators.pattern('http(s?):\\/\\/.+\\.(jpg|jpeg|png)')]),
    gender: new FormControl('', [Validators.required]),
    description: new FormControl(''),
    likes: new FormControl(0)
  });

  constructor(private router: Router, private booksService: BooksService) {
  }

  get genders() {
    return ['Adventure', 'Fantasy', 'Thriller', 'Romantic', 'Child'];
  }

  ngOnInit(): void {
    this.authors$ = this.booksService.getAuthors();
  }

  handleCreate() {
    if (!this.selectAuthor) {
      const newAuthor = this.createBookForm.controls.author.value;
      this.booksService.addAuthor(newAuthor).subscribe(console.log);
    }

    this.booksService.createBook(this.createBookForm.value).subscribe(resp => {
      this.router.navigate(['/']);
    });
  }

  getErrorMessage(fieldName) {
    if (this.createBookForm.controls[fieldName].hasError('pattern')) {
      return 'Invalid image URL';
    }

    if (this.createBookForm.controls[fieldName].hasError('required')) {
      return 'Please enter a value';
    }

    return '';
  }

  addNewForm() {
    this.selectAuthor = false;
    this.createBookForm.controls.author = new FormGroup({
      fullName: new FormControl(''),
      avatarLink: new FormControl('')
    });
  }

}
