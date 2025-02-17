import {Component} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {UserService} from '../../services/user/user.service';
import {LoaderService} from '../../shared/loader/loader.service';
import {InfoSnackbarService} from '../../shared/snackbar/info-snackbar.service';
import {map} from 'rxjs/operators';
import {getErrorMessage} from '../../shared/sharedFunctions';

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
              private fb: FormBuilder,
              private snackBar: InfoSnackbarService) {

    this.registerForm = this.fb.group({
      username: new FormControl('',
        [Validators.required, Validators.minLength(4)],
        [this.checkUsername.bind(this)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      avatarUrl: new FormControl('', [Validators.pattern('http(s?):\\/\\/.+\\.(jpg|jpeg|png)')]),
      password: new FormControl('', [Validators.required, Validators.minLength(6)]),
      confirmPassword: new FormControl('', [Validators.required, Validators.minLength(6)]),
      birthday: new FormControl('', [Validators.required])
    });
  }

  checkUsername(control: AbstractControl) {
    return this.userService.asyncValidatorUsername(control.value)
      .pipe(
        map(res => {
          // @ts-ignore
          return res.usernameExists ? {usernameIsTaken: true} : null;
        })
      );
  }

  getErrorMessage(fieldName: string) {
    return getErrorMessage(this.registerForm.controls[fieldName]);
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
      birthday: new Date(this.registerForm.value.birthday).toLocaleDateString(),
      avatarUrl: this.registerForm.value.avatarUrl
    };
    this.userService.register(userData)
      .subscribe({
        next: () => this.handleSuccess(),
        error: () => this.handleError()
      });
  }

  handleSuccess() {
    this.loader.isLoading = false;
    this.snackBar.openSnackBar('You successfully registered!', 'Success');

    this.router.navigate(['/login']).catch(() => this.handleError());
  }

  handleError() {
    this.loader.isLoading = false;
    this.snackBar.openSnackBar('Something went wrong', 'Error');
  }
}
