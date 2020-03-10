import {Injectable} from '@angular/core';
import {RequesterService} from '../requester/requester.service';
import {map} from 'rxjs/operators';
import {Book} from '../../book/book.model';
import {UserService} from '../user/user.service';

@Injectable({
  providedIn: 'root'
})
export class BooksService {
  private readonly appKey = 'kid_S10Q4H5NU';
  private url = `https://baas.kinvey.com`;

  constructor(private requester: RequesterService, private userService: UserService) {
  }

  getBooks() {
    return this.requester.get(`${this.url}/appdata/${this.appKey}/books`, {
      Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
    }).pipe(
      map(resp => {
        // @ts-ignore
        return resp.map(b => {
          const book = new Book(b._id, b.title, b.author, b.gender, b.description, b.likes, b.imageUrl);

          book.isLiked = this.userService.userLikesBook(book.id);

          return book;
        });
      })
    );
  }

  getBook(id: string) {
    return this.requester.get(`${this.url}/appdata/${this.appKey}/books/${id}`, {
      Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
    }).pipe(
      map(b => {
        // @ts-ignore
        return new Book(b._id, b.title, b.author, b.gender, b.description, b.likes, b.imageUrl);
      })
    );
  }

  getAuthors() {
    return this.requester.get(`${this.url}/appdata/${this.appKey}/authors`, {
      Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
    }).pipe(
      map(resp => {
        // @ts-ignore
        return resp.map(author => {
          return {
            fullName: author.fullName,
            avatarLink: author.avatarLink
          };
        });
      }));
  }

  createBook(book) {
    return this.requester.post(`${this.url}/appdata/${this.appKey}/books`, book, {
      Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
    });
  }

  addLikes(book: Book) {
    book.likes = Number(book.likes) + 1;

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
