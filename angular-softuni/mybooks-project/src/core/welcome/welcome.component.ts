import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {InfoSnackbarService} from '../../shared/snackbar/info-snackbar.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.scss']
})
export class WelcomeComponent implements OnInit {

  constructor(private router: Router, private snackBar: InfoSnackbarService) {
  }

  ngOnInit(): void {
    if (!!sessionStorage.getItem('authtoken')) {
      this.router.navigate(['/books']).catch(() => {
        this.snackBar.openSnackBar('Something went wrong', 'Error');
      });
    }
  }

}
