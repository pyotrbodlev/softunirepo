import {Injectable} from '@angular/core';
import {RequesterService} from '../requester/requester.service';
import {map, switchMap, tap} from 'rxjs/operators';
import {Book, BookShortInfo} from '../../book/book.model';
import {UserService} from '../user/user.service';

@Injectable({
  providedIn: 'root'
})
export class BooksService {
  private readonly appKey = 'kid_S10Q4H5NU';
  private url = `https://baas.kinvey.com`;

  constructor(private requester: RequesterService, private userService: UserService) {
  }

  loadBooks() {
    return this.requester.get(`${this.url}/appdata/${this.appKey}/books`, {
      headers: {
        Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
      }
    }).pipe(
      map(resp => {
        // @ts-ignore
        return resp.map(b => {
          const book = new Book(b._id, b.title, b.author, b.descriptionShort, b.descriptionFull, b.likes, b.imageUrl);

          book.isLiked = this.userService.bookIsLiked(book.id);

          return book;
        });
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

  addLikes(book: Book) {
    book.likes = Number(book.likes) + 1;
    console.log(book);

    return this.requester.put(`${this.url}/appdata/${this.appKey}/books/${book.id}`, book, {
      Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
    });
  }

  removeLikes(book: Book) {
    book.likes = Number(book.likes) - 1;
    if (book.likes < 0) {
      book.likes = 0;
    }

    return this.requester.put(`${this.url}/appdata/${this.appKey}/books/${book.id}`, book, {
      Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
    });
  }
}
