import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Cause} from '../../models/cause.model';
import {CauseService} from '../../services/cause.service';

@Component({
  selector: 'app-cause',
  templateUrl: './cause.component.html',
  styleUrls: ['./cause.component.css']
})
export class CauseComponent {
  @Input() cause: Cause;
  @Output() deleteCauseEvent: EventEmitter<any> = new EventEmitter<any>();

  deleteCause(id) {
    this.deleteCauseEvent.emit(id);
  }
}
