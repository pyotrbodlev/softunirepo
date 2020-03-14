import {Component, Inject, Input} from '@angular/core';
import {MAT_SNACK_BAR_DATA} from "@angular/material/snack-bar";

@Component({
  selector: 'snack-bar-component-info',
  templateUrl: 'snack-bar-component-info.html'
})
export class PizzaPartyComponent {
  message: string;
  color: string;

  constructor(@Inject(MAT_SNACK_BAR_DATA) public data: any) {
    this.message = this.data.message;
    this.color = this.data.color;
  }
}
