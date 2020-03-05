import {Component, OnInit, ViewChild} from '@angular/core';
import {MatMenuTrigger} from '@angular/material/menu';
import {faHatWizard} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  userIsLoggedIn = false;
  @ViewChild(MatMenuTrigger) trigger: MatMenuTrigger;
  faHatWizard = faHatWizard;
}
