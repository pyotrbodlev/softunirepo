import {Component, EventEmitter, Input, Output} from '@angular/core';
import {faHeart} from '@fortawesome/free-solid-svg-icons';
import {Book} from '../book.model';

@Component({
  selector: 'app-book-list-item',
  templateUrl: './book-list-item.component.html',
  styleUrls: ['./book-list-item.component.scss']
})
export class BookListItemComponent {
  faHeart = faHeart;

  @Input() book: Book;
  @Output() userLikedBook: EventEmitter<any> = new EventEmitter<any>();
  @Output() userUnlikeBook: EventEmitter<any> = new EventEmitter<any>();

  constructor() {
  }

  get iconStyle() {
    return this.book.isLiked ? {color: 'red'} : {color: 'darkgrey'};
  }

  get style() {
    return {'background-image': `url(${this.book.author.avatarLink})`};
  }

  get description() {
    return this.book.description.substring(0, 255) + '...';
  }

  handleLike() {
    if (!this.book.isLiked) {
      this.userLikedBook.emit(this.book);
    } else {
      this.userUnlikeBook.emit(this.book);
    }
    this.book.isLiked = !this.book.isLiked;
  }

}
