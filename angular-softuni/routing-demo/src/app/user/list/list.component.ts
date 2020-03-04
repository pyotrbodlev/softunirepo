import {Component, OnInit} from '@angular/core';
import {UserService} from '../user.service';
import {User} from '../user';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {
  get users(): User[] {
    return this.userService.userList;
  }

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.loadPeople();
  }
}
