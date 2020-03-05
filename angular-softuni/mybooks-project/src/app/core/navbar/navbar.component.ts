import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatMenuTrigger} from '@angular/material/menu';
import {faHatWizard} from '@fortawesome/free-solid-svg-icons';
import {UserService} from '../../services/user/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  @ViewChild(MatMenuTrigger) trigger: MatMenuTrigger;
  faHatWizard = faHatWizard;

  constructor(private userService: UserService) {
  }

  get userIsLoggedIn() {
    return !!sessionStorage.getItem('authtoken');
  }

  logout() {
    this.userService.logOut().subscribe((resp) => {
      console.log(resp);
      sessionStorage.clear();
    });
  }
}
