import {Injectable} from '@angular/core';
import {RequesterService} from '../requester/requester.service';
import {count, delay, filter, map, tap} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {IUser} from '../../shared/IUser';

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

  getUserById(id: string): Observable<IUser> {
    return this.requester.get<IUser>(`${this.url}/user/${this.appKey}/${id}`, {
      Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
    });
  }

  logIn(userData): Observable<any> {
    return this.requester.post(`${this.url}/user/${this.appKey}/login`, userData);
  }

  logOut(): Observable<any> {
    return this.requester.post(`${this.url}/user/${this.appKey}/_logout`, {}, {
      Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
    });
  }

  register(userData): Observable<any> {
    return this.requester.post(`${this.url}/user/${this.appKey}`, userData);
  }

  likeBook(bookId): Observable<any> {
    const user = this.currentUser;

    if (!user.likes) {
      user.likes = [];
    }
    user.likes.push(bookId);

    return this.requester.put(`${this.url}/user/${this.appKey}/${this.currentUser._id}`, user, {
      Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
    });
  }

  unlikeBook(bookId): Observable<any> {
    if (this.currentUser.likes) {
      const user = this.currentUser;
      user.likes = this.currentUser.likes.filter(like => like !== bookId);

      return this.requester.put(`${this.url}/user/${this.appKey}/${this.currentUser._id}`, user, {
        Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
      });
    }
  }

  userLikesBook(bookId): boolean {
    const user = this.currentUser;
    if (!user.likes) {
      user.likes = [];
    }
    return user.likes.includes(bookId);
  }

  asyncValidatorUsername(username): Observable<boolean> {
    return this.requester.get(`${this.url}/user/${this.appKey}/`, {
      Authorization: 'Basic a2lkX1MxMFE0SDVOVTozZWM4YTM4MjAyYTk0NTQ5ODJkMWE4NGI5OTZkNWRkMQ=='
    }).pipe(
      count(resp => {
        // @ts-ignore
        const filtered = resp.filter(user => user.username === username);
        return filtered.length;
      }),
      map(usersCount => usersCount > 0)
    );
  }

  updateUserInfo(newData): Observable<IUser> {
    return this.requester.put(`${this.url}/user/${this.appKey}/${this.currentUser._id}`, newData, {
      Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
    });
  }
}
