import {Component} from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {UserService} from '../../services/user/user.service';

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

  constructor(private router: Router, private http: HttpClient, private userService: UserService) {
  }

  getErrorMessage(field: string) {
    if (this.loginForm.controls[field].hasError('required')) {
      return 'You must enter a value';
    } else if (this.loginForm.controls[field].hasError('minlength')) {
      return 'Minimum 5 symbols';
    }

    return this.loginForm.controls[field].hasError(field) ? `Not a valid ${field}` : '';
  }

  handleLogin() {
    this.userService.logIn(this.loginForm.value)
      .subscribe({
        next: resp => this.handleSuccess(resp),
        error: err => console.error(err)
      });
  }

  handleSuccess(resp) {
    sessionStorage.setItem('authtoken', resp._kmd.authtoken);
    this.router.navigate(['/']);
  }
}
