import {Injectable} from '@angular/core';
import {RequesterService} from '../requester/requester.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly appKey = 'kid_S10Q4H5NU';
  private url = `https://baas.kinvey.com`;

  constructor(private requester: RequesterService) {
  }

  logIn(userData) {
    console.log(userData);
    return this.requester.post(`${this.url}/user/${this.appKey}/login`, userData);
  }

  logOut() {
    return this.requester.post(`${this.url}/user/${this.appKey}/_logout`, {}, {
      Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
    });
  }

  register(userData) {
    return this.requester.post(`${this.url}/user/${this.appKey}`, userData);
  }
}
