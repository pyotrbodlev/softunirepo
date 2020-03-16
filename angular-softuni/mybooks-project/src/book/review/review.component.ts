import {Component, Input, OnInit} from '@angular/core';
import {IReview} from './review.model';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.scss']
})
export class ReviewComponent implements OnInit {
  @Input() reviewInfo: IReview;
  defaultAvatarUrl = 'https://i1.wp.com/quaan.one/wp-content/uploads/2018/08/default-avatar.jpg?ssl=1';

  constructor() {
  }

  ngOnInit(): void {
  }

}
