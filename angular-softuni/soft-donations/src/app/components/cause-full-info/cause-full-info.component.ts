import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Cause} from '../../models/cause.model';

@Component({
  selector: 'app-cause-full-info',
  templateUrl: './cause-full-info.component.html',
  styleUrls: ['./cause-full-info.component.css']
})
export class CauseFullInfoComponent  {
  @Input() cause: Cause;
  @Output() addMoneyEvent: EventEmitter<any> = new EventEmitter<any>();

  get currentMoneyColor() {
    return this.cause.currentMoney > this.cause.moneyNeeded ? 'green' : 'orange';
  }

  addMoney(amount) {
    this.addMoneyEvent.emit(amount);
  }
}
