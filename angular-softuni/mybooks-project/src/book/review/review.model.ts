export interface IReview {
    user: {
        username: string,
        _id: string,
        avatarUrl: string
    };
    _kmd: {
        ect: string
    };
    review: string;
}

export class Review implements IReview {
    public user: {
        username: string,
        _id: string,
        avatarUrl: string
    };

    public _kmd: {
        ect: string
    };

    public review: string;

    constructor(user, review, _kmd) {
        this.user = user;
        this.review = review;
        this._kmd = _kmd;
    }
}