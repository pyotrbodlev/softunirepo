import {Injectable} from '@angular/core';
import {RequesterService} from '../requester/requester.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private requester: RequesterService) {
  }

  logIn(userData) {
    console.log(userData);
    return this.requester.post(userData);
  }
}
