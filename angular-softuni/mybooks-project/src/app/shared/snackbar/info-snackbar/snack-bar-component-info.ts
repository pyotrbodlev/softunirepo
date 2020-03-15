import {Component, Inject} from '@angular/core';
import {MAT_SNACK_BAR_DATA} from "@angular/material/snack-bar";

@Component({
  selector: 'snack-bar-component-info',
  templateUrl: 'snack-bar-component-info.html',
  styleUrls: ['snack-bar-component-info.scss']
})
export class SnackBarInfoComponent {
  message: string;
  color: string;

  constructor(@Inject(MAT_SNACK_BAR_DATA) public data: any) {
    this.message = this.data.message;
    this.color = this.data.color;
  }
}
