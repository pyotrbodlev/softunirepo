import {Injectable} from '@angular/core';
import {map} from 'rxjs/operators';
import {Book} from '../../book/book.model';
import {UserService} from '../user/user.service';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BooksService {
  private readonly appKey = 'kid_S10Q4H5NU';
  private url = `https://baas.kinvey.com`;

  constructor(private http: HttpClient, private userService: UserService) {
  }

  getBooks(): Observable<Book[]> {
    return this.http.get(`${this.url}/appdata/${this.appKey}/books`).pipe(
      map(resp => {
        // @ts-ignore
        return resp.map(b => {
          const book = new Book(b._id, b.title, b.author, b.gender, b.description, b.likes, b.imageUrl, b._acl);

          book.isLiked = this.userService.userLikesBook(book._id);
          return book;
        });
      })
    );
  }

  getBook(id: string): Observable<Book> {
    return this.http.get(`${this.url}/appdata/${this.appKey}/books/${id}`).pipe(
      map(b => {
        // @ts-ignore
        return new Book(b._id, b.title, b.author, b.gender, b.description, b.likes, b.imageUrl);
      })
    );
  }

  getAuthors() {
    return this.http.get(`${this.url}/appdata/${this.appKey}/authors`).pipe(
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

  addAuthor(author) {
    return this.http.post(`${this.url}/appdata/${this.appKey}/authors`, author);
  }

  createBook(book) {
    return this.http.post(`${this.url}/appdata/${this.appKey}/books`, book);
  }

  addLikes(book: Book) {
    book.likes = Number(book.likes) + 1;

    return this.http.put(`${this.url}/appdata/${this.appKey}/books/${book._id}`, book);
  }

  removeLikes(book: Book) {
    book.likes = Number(book.likes) - 1;
    if (book.likes < 0) {
      book.likes = 0;
    }

    return this.http.put(`${this.url}/appdata/${this.appKey}/books/${book._id}`, book);
  }
}
