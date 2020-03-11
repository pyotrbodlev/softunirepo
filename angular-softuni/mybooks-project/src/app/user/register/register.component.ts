import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {UserService} from '../../services/user/user.service';
import {LoaderService} from '../../shared/loader/loader.service';
import {map} from 'rxjs/operators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  startDate = Date.now();
  hidePassword = true;
  hideConfirmPassword = true;
  usernameIsTaken: boolean;
  registerForm: FormGroup;

  constructor(private router: Router,
              private http: HttpClient,
              private userService: UserService,
              private loader: LoaderService,
              private fb: FormBuilder) {

    this.registerForm = this.fb.group({
      username: new FormControl('',
        [Validators.required, Validators.minLength(4)],
        [this.checkUsername.bind(this)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      confirmPassword: new FormControl('', [Validators.required, Validators.minLength(6)]),
      birthday: new FormControl('', [Validators.required])
    });
  }

  checkUsername(control: AbstractControl) {
    return this.userService.asyncValidatorUsername(control.value)
      .pipe(
        map(res => {
          return res ? {usernameIsTaken: true} : null;
        })
      );
  }

  getErrorMessage(field: string) {
    if (this.registerForm.controls[field].hasError('required')) {
      return 'You must enter a value';
    }
    if (this.registerForm.controls[field].hasError('minlength')) {
      const minLength = this.registerForm.controls[field].errors.minlength.requiredLength;
      return `Minimum ${minLength} symbols`;
    }
    if (this.registerForm.controls[field].hasError('passwords-doesnt-match')) {
      return 'Passwords doesnt match';
    }
    if (this.registerForm.controls[field].hasError('email')) {
      return 'Invalid email';
    }
    if (this.registerForm.controls[field].hasError('usernameIsTaken')) {
      return 'Username is already exist. Please use another username';
    }

    return this.registerForm.controls[field].hasError(field) ? `Not a valid ${field}` : '';
  }

  checkPass() {
    if (this.registerForm.controls.confirmPassword.value !== this.registerForm.controls.password.value) {
      this.registerForm.controls.confirmPassword.setErrors({
        'passwords-doesnt-match': true
      });
    }
  }

  handleRegister() {
    this.loader.isLoading = true;
    const userData = {
      username: this.registerForm.value.username,
      email: this.registerForm.value.email,
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
