import {Component, Input, OnInit} from '@angular/core';

interface IReview {
  user: {
    username: string,
    _id: string
  };
  review: string;
}

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.scss']
})
export class ReviewComponent implements OnInit {
  @Input() reviewInfo: IReview;

  constructor() {
  }

  ngOnInit(): void {
  }

}
