import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/user/user.service';
import {ActivatedRoute} from '@angular/router';
import {IUser} from '../../shared/IUser';
import {LoaderService} from '../../shared/loader/loader.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Book} from '../../book/book.model';
import {BooksService} from '../../services/books/books.service';
import {map, shareReplay} from 'rxjs/operators';
import {Observable} from 'rxjs';

function checkPasswords(control: FormGroup) {
  if (control.controls.confirmPassword.touched) {
    if (control.value.password !== control.value.confirmPassword) {
      return {'passwords-doesnt-match': true};
    }
  }
  return null;
}

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  userInfo: IUser;
  newUserInfoFormGroup: FormGroup;
  successMessage: string;
  defaultAvatarUrl = 'https://i1.wp.com/quaan.one/wp-content/uploads/2018/08/default-avatar.jpg?ssl=1';
  likedBooks: Observable<Book[]>;
  addedBooks: Observable<Book[]>;

  constructor(private userService: UserService,
              private booksService: BooksService,
              private router: ActivatedRoute,
              private loader: LoaderService,
              private fb: FormBuilder) {

    this.newUserInfoFormGroup = fb.group({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      confirmPassword: new FormControl('', [Validators.required, Validators.minLength(6)])
    }, {validators: checkPasswords});
  }

  getErrorMessage(fieldName: string) {
    if (this.newUserInfoFormGroup.controls[fieldName].hasError('required')) {
      return 'You must enter a value';
    }
    if (this.newUserInfoFormGroup.controls[fieldName].hasError('passwords-doesnt-match')) {
      return 'Passwords doesnt match';
    }
    if (this.newUserInfoFormGroup.controls[fieldName].hasError('email')) {
      return 'Invalid email';
    }
    if (this.newUserInfoFormGroup.controls[fieldName].hasError('minlength')) {
      const minLength = this.newUserInfoFormGroup.controls[fieldName].errors.minlength.requiredLength;
      return `Minimum ${minLength} symbols`;
    }

    return this.newUserInfoFormGroup.controls[fieldName].hasError(fieldName) ? `Not a valid ${fieldName}` : '';
  }

  ngOnInit(): void {
    this.loader.isLoading = true;
    const userId = this.router.snapshot.params.id;

    this.userService.getUserById(userId).subscribe(user => {
      this.loader.isLoading = false;
      this.userInfo = user;
    });

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

  updateUserInfo() {
    this.loader.isLoading = true;
    const newUserData = {
      email: this.newUserInfoFormGroup.controls.email.value,
      password: this.newUserInfoFormGroup.controls.email.value
    };

    this.userService.updateUserInfo(newUserData).subscribe({
      next: resp => {
        this.loader.isLoading = false;
        sessionStorage.setItem('me', JSON.stringify(resp));
        sessionStorage.setItem('authtoken', resp._kmd.authtoken);
        this.newUserInfoFormGroup.reset();
        this.successMessage = 'You successfully changed your info!';
      },
      error: err => console.error(err)
    });
  }
}
