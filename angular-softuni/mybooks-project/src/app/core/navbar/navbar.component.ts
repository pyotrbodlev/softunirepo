import {Component, ViewChild} from '@angular/core';
import {MatMenuTrigger} from '@angular/material/menu';
import {faHatWizard} from '@fortawesome/free-solid-svg-icons';
import {UserService} from '../../services/user/user.service';
import {LoaderService} from '../../shared/loader/loader.service';
import {Router} from '@angular/router';
import {InfoSnackbarService} from "../../shared/snackbar/info-snackbar.service";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  @ViewChild(MatMenuTrigger) trigger: MatMenuTrigger;
  faHatWizard = faHatWizard;

  constructor(private userService: UserService, private loader: LoaderService, private router: Router, private snackBar: InfoSnackbarService) {
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
          this.loader.isLoading = false;
          sessionStorage.clear();
          this.router.navigate(['/']).catch(() => {
            this.snackBar.openSnackBar('Something went wrong', 'Error');
          });
        });
  }
}
