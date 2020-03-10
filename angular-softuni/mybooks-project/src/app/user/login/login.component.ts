import {Component} from '@angular/core';
import {FormGroup, FormControl, Validators, FormBuilder} from '@angular/forms';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {UserService} from '../../services/user/user.service';
import {LoaderService} from '../../shared/loader/loader.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  hide = true;
  unauthorised = false;
  loginForm: FormGroup;

  constructor(private router: Router,
              private http: HttpClient,
              private userService: UserService,
              private loader: LoaderService,
              private fb: FormBuilder) {

    this.loginForm = this.fb.group({
      username: new FormControl('', [Validators.required, Validators.minLength(4)]),
      password: new FormControl('', [Validators.required, Validators.minLength(4)]),
    });
  }

  getErrorMessage(field: string) {
    if (this.loginForm.controls[field].hasError('required')) {
      return 'You must enter a value';
    }

    if (this.loginForm.controls[field].hasError('minlength')) {
      const minLength = this.loginForm.controls[field].errors.minlength.requiredLength;
      return `Minimum ${minLength} symbols`;
    }

    return this.loginForm.controls[field].hasError(field) ? `Not a valid ${field}` : '';
  }

  handleLogin() {
    this.loader.isLoading = true;
    this.userService.logIn(this.loginForm.value)
      .subscribe({
        next: resp => {
          this.handleSuccess(resp);
        },
        error: err => this.handleError(err)
      });
  }

  handleSuccess(resp) {
    this.loader.isLoading = false;
    sessionStorage.setItem('me', JSON.stringify(resp));
    sessionStorage.setItem('authtoken', resp._kmd.authtoken);
    this.router.navigate(['/']);
  }

  handleError(err) {
    this.loader.isLoading = false;

    if (err.status === 401) {
      this.unauthorised = true;
    } else {
      console.log(err);
    }
  }

  showError(status: number) {
    if (status === 401) {
      return 'Invalid password or username!';
    }
  }
}
