import {Injectable} from '@angular/core';
import {RequesterService} from '../requester/requester.service';

interface IUser {
  _id: string;
  username: string;
  birthday: string;
  likes: string[];
  _kmd: {
    lmt: string,
    ect: string,
  };
  _acl: {
    creator: string
  };
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly appKey = 'kid_S10Q4H5NU';
  private url = `https://baas.kinvey.com`;

  constructor(private requester: RequesterService) {
  }

  get currentUser(): IUser {
    return JSON.parse(sessionStorage.getItem('me'));
  }

  logIn(userData) {
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

  likeBook(bookId) {
    const user = this.currentUser;

    if (!user.likes) {
      user.likes = [];
    }
    user.likes.push(bookId);

    return this.requester.put(`${this.url}/user/${this.appKey}/${this.currentUser._id}`, user, {
      Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
    });
  }

  unlikeBook(bookId) {
    if (this.currentUser.likes) {
      const user = this.currentUser;
      user.likes = this.currentUser.likes.filter(like => like !== bookId);

      return this.requester.put(`${this.url}/user/${this.appKey}/${this.currentUser._id}`, user, {
        Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
      });
    }
  }

  bookIsLiked(bookId) {
    const user = this.currentUser;
    if (!user.likes) {
      user.likes = [];
    }
    return user.likes.includes(bookId);
  }
}
