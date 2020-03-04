import {Component, EventEmitter, Input, OnDestroy, Output} from '@angular/core';
import {Cause} from '../../models/cause.model';
import {CauseService} from '../../services/cause.service';

@Component({
  selector: 'app-cause',
  templateUrl: './cause.component.html',
  styleUrls: ['./cause.component.css']
})
export class CauseComponent implements OnDestroy {
  @Input() cause: Cause;
  @Output() deleteCauseEvent: EventEmitter<any> = new EventEmitter<any>();

  deleteCause(id) {
    this.deleteCauseEvent.emit(id);
  }

  ngOnDestroy(): void {

  }
}
