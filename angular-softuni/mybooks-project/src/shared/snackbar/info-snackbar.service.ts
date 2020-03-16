import {Injectable} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import {SnackBarInfoComponent} from './info-snackbar/snack-bar-component-info';

@Injectable({
  providedIn: 'root'
})
export class InfoSnackbarService {
  durationInSeconds = 4;

  constructor(private _snackBar: MatSnackBar) {
  }

  openSnackBar(message, type) {
    this._snackBar.openFromComponent(SnackBarInfoComponent, {
      duration: this.durationInSeconds * 1000,
      data: {
        message,
        color: type === 'Success' ? 'white' : 'black'
      },
      panelClass: ['mat-toolbar', type === 'Success' ? 'mat-primary' : 'mat-warn']
    });
  }
}
