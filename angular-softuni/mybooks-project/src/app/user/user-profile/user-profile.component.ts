import {Component, ElementRef, OnInit} from '@angular/core';
import {UserService} from '../../services/user/user.service';
import {ActivatedRoute} from '@angular/router';
import {IUser} from '../../shared/IUser';
import {LoaderService} from '../../shared/loader/loader.service';
import {AbstractControl, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators} from '@angular/forms';

function checkPasswords(control: AbstractControl) {
  if (control.value.password !== control.value.confirmPassword){
    return {'passwords-doesnt-match': true};
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

  constructor(private userService: UserService,
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
  }

}
