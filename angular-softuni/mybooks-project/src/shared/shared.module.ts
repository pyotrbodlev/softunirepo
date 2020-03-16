import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {SnackBarInfoComponent} from './snackbar/info-snackbar/snack-bar-component-info';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import {MAT_SNACK_BAR_DEFAULT_OPTIONS, MatSnackBarModule} from '@angular/material/snack-bar';

@NgModule({
  declarations: [SnackBarInfoComponent],
  imports: [
    CommonModule,
    MatFormFieldModule,
    FormsModule,
    MatSnackBarModule
  ],
  providers: [
    {provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: {duration: 1000}}
  ],
  exports: [SnackBarInfoComponent]
})
export class SharedModule {
}
