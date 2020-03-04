import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Cause} from '../../models/cause.model';
import {CauseService} from '../../services/cause.service';

@Component({
  selector: 'app-causes',
  templateUrl: './causes.component.html',
  styleUrls: ['./causes.component.css']
})
export class CausesComponent implements OnInit {

  causes: Cause[];
  causeService: CauseService;
  @Output() showMoreInfo: EventEmitter<any> = new EventEmitter<any>();

  constructor(causeService: CauseService) {
    this.causeService = causeService;
  }

  ngOnInit(): void {
    this.causeService
      .getCauses()
      .subscribe(c => {
        this.causes = c;
      });
  }

  showCauseFullInfo(ev) {
    const target: Element = ev.currentTarget.firstChild;
    const causeId = target.getAttribute('cause-id');
    this.showMoreInfo.emit(causeId);
  }

  handleDelete(id) {
    this.causeService.deleteCause(id).subscribe(() => {
      this.causes = this.causes.filter(c => c.id !== id);
    });
  }
}
