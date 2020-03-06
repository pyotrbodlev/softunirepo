import {Injectable} from '@angular/core';
import {RequesterService} from '../requester/requester.service';
import {map, tap} from 'rxjs/operators';
import {Book, BookShortInfo} from '../../book/book.model';

@Injectable({
  providedIn: 'root'
})
export class BooksService {
  private readonly appKey = 'kid_S10Q4H5NU';
  private url = `https://baas.kinvey.com`;
  isLoading = false;

  constructor(private requester: RequesterService) {
  }

  loadBooks() {
    return this.requester.get(`${this.url}/appdata/${this.appKey}/books`, {
      headers: {
        Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
      }
    })
      .pipe(
        map(resp => {
          // @ts-ignore
          return resp.map(b => new BookShortInfo(b._id, b.title, b.author, b.descriptionShort, b.likes, b.imageUrl));
        })
      );
  }

  getBook(id: string) {
    return this.requester.get(`${this.url}/appdata/${this.appKey}/books/${id}`, {
      headers: {
        Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
      }
    }).pipe(
      map(b => {
        // @ts-ignore
        return new Book(b._id, b.title, b.author, b.descriptionShort, b.descriptionFull, b.likes, b.imageUrl);
      })
    );
  }
}
