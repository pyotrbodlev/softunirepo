import {Injectable} from '@angular/core';
import {map, shareReplay} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';

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

  constructor(private http: HttpClient) {
  }

  loadReviews(bookId) {
    return this.http.get(`https://baas.kinvey.com/appdata/kid_S10Q4H5NU/reviews`)
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
        }),
        map(reviews => reviews.length > 0 ? reviews : null),
        shareReplay(1)
      );
  }

  addReview(body) {
    return this.http.post(`https://baas.kinvey.com/appdata/kid_S10Q4H5NU/reviews`, body);
  }
}
