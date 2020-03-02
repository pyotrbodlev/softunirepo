import {Component} from '@angular/core';
import {Cause} from '../../models/cause.model';
import {CauseService} from '../../services/cause.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent {
  private causeService: CauseService;
  cause: Cause;

  constructor(causeService: CauseService) {
    this.causeService = causeService;
  }

  handleShowMoreInfo(id) {
    this.causeService.getCauseById(id).subscribe(c => this.cause = c);
  }

  handleAddMoney(amount) {
    if (amount > 0) {
      this.causeService.donateMoney(this.cause, amount).subscribe(resp => {
        console.log(resp);
      });
    }
  }
}
