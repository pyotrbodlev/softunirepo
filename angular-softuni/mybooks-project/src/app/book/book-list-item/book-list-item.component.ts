import {Component, Input, OnInit} from '@angular/core';
import {faHeart} from '@fortawesome/free-solid-svg-icons';
import {BookShortInfo} from '../book.model';

@Component({
  selector: 'app-book-list-item',
  templateUrl: './book-list-item.component.html',
  styleUrls: ['./book-list-item.component.scss']
})
export class BookListItemComponent implements OnInit {
  faHeart = faHeart;
  @Input() book: BookShortInfo;

  constructor() {
  }

  get style() {
    return {'background-image': `url(${this.book.author.avatarLink})`};
  }

  get description() {
    return this.book.descriptionShort.substring(0, 255) + '...';
  }

  ngOnInit(): void {
  }

}
