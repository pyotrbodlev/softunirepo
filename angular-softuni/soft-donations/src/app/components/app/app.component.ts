import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Soft Donations';
  createNewCause = false;

  handleNewCreate() {
    this.createNewCause = !this.createNewCause;
  }

  handleCreateNewCause() {
    this.createNewCause = false;
  }
}
