import {Injectable} from '@angular/core';
import {map, shareReplay} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {Review} from "../../book/review/review.model";

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
                    return resp.map(reviewInfo => new Review(reviewInfo.user, reviewInfo.review, reviewInfo._kmd));
                }),
                map(reviews => reviews.length > 0 ? reviews : null),
                shareReplay(1)
            );
    }

    addReview(body) {
        return this.http.post(`https://baas.kinvey.com/appdata/kid_S10Q4H5NU/reviews`, body);
    }
}
