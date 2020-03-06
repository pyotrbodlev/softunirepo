import {Component} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';
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
  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  constructor(private router: Router, private http: HttpClient, private userService: UserService, private loader: LoaderService) {
  }

  getErrorMessage(field: string) {
    if (this.loginForm.controls[field].hasError('required')) {
      return 'You must enter a value';
    }

    if (this.loginForm.controls[field].hasError('minlength')) {
      return 'Minimum 5 symbols';
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
        error: err => console.error(err)
      });
  }

  handleSuccess(resp) {
    this.loader.isLoading = false;
    sessionStorage.setItem('authtoken', resp._kmd.authtoken);
    this.router.navigate(['/']);
  }
}
