import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {CauseService} from '../../services/cause.service';
import {Cause} from '../../models/cause.model';

@Component({
  selector: 'app-create-new-cause',
  templateUrl: './create-new-cause.component.html',
  styleUrls: ['./create-new-cause.component.css']
})
export class CreateNewCauseComponent implements OnInit {
  private causeService: CauseService;
  @Output() createCauseEvent: EventEmitter<any> = new EventEmitter<any>();

  constructor(causeService: CauseService) {
    this.causeService = causeService;
  }

  ngOnInit(): void {
  }

  createCause(event, title, description, imageUrl, moneyNeeded) {
    event.preventDefault();
    this.causeService.createCause({
      title,
      description,
      imageUrl,
      moneyNeeded,
      currentMoney: 0
    }).subscribe(() => this.createCauseEvent.emit(), (err) => console.error(err));
  }
}
