import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../user.service';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit, OnDestroy {
  get currentUser() {
    return this.userService.currentUser;
  }

  set currentUser(newUser) {
    this.userService.currentUser = newUser;
  }

  constructor(private route: ActivatedRoute, private userService: UserService) {
  }

  ngOnInit(): void {
    const currentName = this.route.snapshot.params.name;
    this.userService.loadUser(currentName);
  }

  ngOnDestroy(): void {
    console.log('ha ha ha');
    this.currentUser = null;
  }

}
