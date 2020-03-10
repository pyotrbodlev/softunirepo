import {Injectable} from '@angular/core';
import {RequesterService} from '../requester/requester.service';
import {filter, map} from 'rxjs/operators';

interface IReview {
  user: {
    username: string,
    _id: string
  };
  review: string;
}

class Review implements IReview {
  public user: {
    username: string,
    _id: string
  };

  public review: string;

  constructor(user, review) {
    this.user = user;
    this.review = review;
  }
}

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private requester: RequesterService) {
  }

  loadReviews(bookId) {
    return this.requester.get(`https://baas.kinvey.com/appdata/kid_S10Q4H5NU/reviews`, {
      Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
    })
      .pipe(
        map(resp => {
          // @ts-ignore
          return resp.filter(reviewInfo => {
            return reviewInfo.bookId === bookId;
          });
        }),
        map(resp => {
          // @ts-ignore
          return resp.map(reviewInfo => new Review(reviewInfo.user, reviewInfo.review));
        })
      );
  }

  addReview(body) {
    return this.requester.post(`https://baas.kinvey.com/appdata/kid_S10Q4H5NU/reviews`, body, {
      Authorization: 'Kinvey ' + sessionStorage.getItem('authtoken')
    });
  }
}
