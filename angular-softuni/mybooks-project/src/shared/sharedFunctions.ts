import {AbstractControl, FormGroup} from '@angular/forms';

export const getErrorMessage = (field: AbstractControl) => {
  if (field?.hasError('required')) {
    return 'You must enter a value';
  }
  if (field?.hasError('minlength')) {
    const minLength = field.errors.minlength.requiredLength;
    return `Minimum ${minLength} symbols`;
  }
  if (field?.hasError('passwords-doesnt-match')) {
    return 'Passwords doesnt match';
  }
  if (field?.hasError('email')) {
    return 'Invalid email';
  }
  if (field?.hasError('usernameIsTaken')) {
    return 'Username is already exist. Please use another username';
  }
  if (field?.hasError('pattern')) {
    return 'Invalid image URL';
  }

  return '';
};

export const checkPasswords = (control: FormGroup) => {
  if (control.value.password !== control.value.confirmPassword) {
    return {'passwords-doesnt-match': true};
  }
  return null;
};

