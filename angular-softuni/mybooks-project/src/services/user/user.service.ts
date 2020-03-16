import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {IUser} from '../../shared/IUser';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly appKey = 'kid_S10Q4H5NU';
  private url = `https://baas.kinvey.com`;

  constructor(private http: HttpClient) {
  }

  get currentUser(): IUser {
    return JSON.parse(sessionStorage.getItem('me'));
  }

  getUserById(id: string): Observable<IUser> {
    return this.http.get<IUser>(`${this.url}/user/${this.appKey}/${id}`);
  }

  logIn(userData): Observable<any> {
    return this.http.post(`${this.url}/user/${this.appKey}/login`, userData);
  }

  logOut(): Observable<any> {
    return this.http.post(`${this.url}/user/${this.appKey}/_logout`, {});
  }

  register(userData): Observable<any> {
    return this.http.post(`${this.url}/user/${this.appKey}`, userData);
  }

  likeBook(bookId): Observable<any> {
    const user = this.currentUser;

    if (!user.likes) {
      user.likes = [];
    }
    user.likes.push(bookId);

    return this.http.put(`${this.url}/user/${this.appKey}/${this.currentUser._id}`, user);
  }

  unlikeBook(bookId): Observable<any> {
    if (this.currentUser.likes) {
      const user = this.currentUser;
      user.likes = this.currentUser.likes.filter(like => like !== bookId);

      return this.http.put(`${this.url}/user/${this.appKey}/${this.currentUser._id}`, user);
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
    return this.http.post<boolean>(`${this.url}/rpc/${this.appKey}/check-username-exists`, {username});
  }

  updateUserInfo(newData): Observable<IUser> {
    return this.http.put<IUser>(`${this.url}/user/${this.appKey}/${this.currentUser._id}`, newData);
  }
}
