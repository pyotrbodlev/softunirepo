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
  currentUser: IUser = JSON.parse(sessionStorage.getItem('me'));

  constructor(private requester: RequesterService) {
  }

  get currentUserId() {
    return this.currentUser._id;
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
    if (!this.currentUser.likes) {
      this.currentUser.likes = [];
    }
    this.currentUser.likes.push(bookId);
    console.log(this.currentUser);

    return this.requester.put(`${this.url}/user/${this.appKey}/${this.currentUser._id}`, this.currentUser, {
      Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
    });
  }

  unlikeBook(bookId) {
    if (this.currentUser.likes) {
      this.currentUser.likes = this.currentUser.likes.filter(like => like !== bookId);

      return this.requester.put(`${this.url}/user/${this.appKey}/${this.currentUser._id}`, this.currentUser, {
        Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
      });
    }
  }

  bookIsLiked(bookId) {
    if (!this.currentUser.likes) {
      this.currentUser.likes = [];
    }
    return this.currentUser.likes.includes(bookId);
  }
}
