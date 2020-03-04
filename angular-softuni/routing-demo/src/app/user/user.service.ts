import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map, switchAll, switchMap} from 'rxjs/operators';
import {User} from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private httpClient: HttpClient;
  private url = 'https://swapi.co/api/people';

  public userList: User[];
  public currentUser: User;

  constructor(httpClient: HttpClient) {
    this.httpClient = httpClient;
  }

  loadPeople() {
    this.httpClient.get(this.url)
      .pipe(
        map(resp => {
          // @ts-ignore
          return resp.results;
        }),
        map(
          userArr => userArr.map(user => new User(user.name, user.height, user.mass, user.url))
        )
      )
      .subscribe(users => this.userList = users);
  }

  loadUser(username) {
    this.httpClient.get(this.url + '/?search=' + username).pipe(
      map(resp => {
        // @ts-ignore
        return resp.results;
      })
    ).subscribe(users => this.currentUser = users[0], (error) => console.error(error));
  }
}
