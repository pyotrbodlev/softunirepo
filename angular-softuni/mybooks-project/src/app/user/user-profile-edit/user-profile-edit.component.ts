import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {LoaderService} from "../../shared/loader/loader.service";
import {UserService} from "../../services/user/user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {IUser} from "../../shared/IUser";
import {InfoSnackbarService} from "../../shared/snackbar/info-snackbar.service";

function checkPasswords(control: FormGroup) {
  if (control.value.password !== control.value.confirmPassword) {
    return {'passwords-doesnt-match': true};
  }
  return null;
}

@Component({
  selector: 'app-user-profile-edit',
  templateUrl: './user-profile-edit.component.html',
  styleUrls: ['./user-profile-edit.component.scss']
})
export class UserProfileEditComponent implements OnInit {
  newUserInfoFormGroup: FormGroup;
  userInfo: IUser;
  hide = true;
  hideConfirm = true;

  constructor(private fb: FormBuilder,
              private loader: LoaderService,
              private  userService: UserService,
              private activatedRoute: ActivatedRoute,
              private snackBar: InfoSnackbarService,
              private router: Router) {
    this.newUserInfoFormGroup = fb.group({
      email: new FormControl('', [Validators.email]),
      avatarUrl: new FormControl('', [Validators.pattern('http(s?):\\/\\/.+')]),
      password: new FormControl('', [Validators.minLength(6)]),
      confirmPassword: new FormControl('', [Validators.minLength(6)])
    }, {validators: checkPasswords});
  }

  ngOnInit(): void {
    const userId = this.activatedRoute.snapshot.params.id;

    this.userService.getUserById(userId).subscribe(user => {
      this.loader.isLoading = false;
      this.userInfo = user;
    });
  }

  getErrorMessage(fieldName: string) {
    if (this.newUserInfoFormGroup.controls[fieldName]?.hasError('required')) {
      return 'You must enter a value';
    }
    if (this.newUserInfoFormGroup.controls[fieldName]?.hasError('passwords-doesnt-match')) {
      return 'Passwords doesnt match';
    }
    if (this.newUserInfoFormGroup.controls[fieldName]?.hasError('email')) {
      return 'Invalid email';
    }
    if (this.newUserInfoFormGroup.controls[fieldName]?.hasError('minlength')) {
      const minLength = this.newUserInfoFormGroup.controls[fieldName].errors.minlength.requiredLength;
      return `Minimum ${minLength} symbols`;
    }
    if (fieldName === 'passwords' && this.newUserInfoFormGroup.hasError('passwords-doesnt-match')) {
      return 'Passwords dont match'
    }
    if (this.newUserInfoFormGroup.controls[fieldName]?.hasError('pattern')) {
      return 'Invalid image URL';
    }

    return '';
  }

  updateUserInfo() {
    this.loader.isLoading = true;

    const newUserData = {};

    if (this.newUserInfoFormGroup.controls.email.value) {
      newUserData['email'] = this.newUserInfoFormGroup.controls.email.value;
    } else {
      newUserData['email'] = this.userInfo.email;
    }

    if (this.newUserInfoFormGroup.controls.avatarUrl.value) {
      newUserData['avatarUrl'] = this.newUserInfoFormGroup.controls.avatarUrl.value;
    } else {
      newUserData['avatarUrl'] = this.userInfo.avatarUrl;
    }

    if (this.newUserInfoFormGroup.controls.password.value) {
      newUserData['password'] = this.newUserInfoFormGroup.controls.password.value;
    }

    this.userService.updateUserInfo(Object.assign(this.userInfo, newUserData)).subscribe({
      next: resp => {
        this.loader.isLoading = false;
        sessionStorage.setItem('me', JSON.stringify(resp));
        sessionStorage.setItem('authtoken', resp._kmd.authtoken);
        this.snackBar.openSnackBar('You successfully updated your info!', 'Success');

        this.router.navigate(['/user', this.userInfo._id]).catch(() => {
          this.snackBar.openSnackBar('Something went wrong! Please retry!', 'Error');
        })
      },
      error: () => {
        this.snackBar.openSnackBar('Something went wrong! Please retry!', 'Error');
      }
    });
  }
}
