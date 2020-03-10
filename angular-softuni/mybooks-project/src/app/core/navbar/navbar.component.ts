import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {MatMenuTrigger} from '@angular/material/menu';
import {faHatWizard} from '@fortawesome/free-solid-svg-icons';
import {UserService} from '../../services/user/user.service';
import {LoaderService} from '../../shared/loader/loader.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  @ViewChild(MatMenuTrigger) trigger: MatMenuTrigger;
  faHatWizard = faHatWizard;

  constructor(private userService: UserService, private loader: LoaderService, private router: Router) {
  }

  get isLoading(): boolean {
    return this.loader.isLoading;
  }

  get userIsLoggedIn(): boolean {
    return !!sessionStorage.getItem('authtoken');
  }

  get username(): string {
    return this.userService.currentUser.username;
  }

  get userId(): string {
    return this.userService.currentUser._id;
  }

  handleLogOut() {
    this.loader.isLoading = true;
    this.userService.logOut()
      .subscribe(
        () => {
          this.router.navigate(['/']).then(r => {
            this.loader.isLoading = false;
            sessionStorage.clear();
          });
        });
  }
}
