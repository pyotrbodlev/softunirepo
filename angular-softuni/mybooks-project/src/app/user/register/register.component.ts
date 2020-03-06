import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {UserService} from '../../services/user/user.service';
import {LoaderService} from '../../shared/loader/loader.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  startDate = Date.now();
  hide1 = true;
  hide = true;
  registerForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl(''),
    birthday: new FormControl('')
  });

  constructor(private router: Router, private http: HttpClient, private userService: UserService, private loader: LoaderService) {
  }

  getErrorMessage(field: string) {
    if (this.registerForm.controls[field].hasError('required')) {
      return 'You must enter a value';
    }
    if (this.registerForm.controls[field].hasError('minlength')) {
      return 'Minimum 5 symbols';
    }

    if (this.registerForm.controls[field].hasError('not-same')) {
      return 'Passwords doesnt match';
    }

    return '';
  }

  checkPass() {
    if (this.registerForm.controls.confirmPassword.value !== this.registerForm.controls.password.value) {
      this.registerForm.controls.confirmPassword.setErrors({
        'not-same': true
      });
    }
  }

  handleRegister() {
    this.loader.isLoading = true;
    const userData = {
      username: this.registerForm.value.username,
      password: this.registerForm.value.password,
      birthday: new Date(this.registerForm.value.birthday).toLocaleDateString()
    };
    this.userService.register(userData)
      .subscribe({
        next: resp => this.handleSuccess(resp),
        error: err => console.error(err)
      });
  }

  handleSuccess(resp) {
    this.loader.isLoading = false;
    this.router.navigate(['/login']);
  }

}
