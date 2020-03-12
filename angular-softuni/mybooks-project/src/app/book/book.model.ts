interface IAuthor {
  fullName: string;
  avatarLink: string;
}

interface ICharacter {
  name: string;
  age: number;
}

export class Book {
  // tslint:disable-next-line:variable-name
  public _id: string;
  public title: string;
  public author: IAuthor;
  public description: string;
  public likes: number;
  public isLiked: boolean;
  public imageUrl: string;
  public gender: string;
  // tslint:disable-next-line:variable-name
  public _acl: {creator: string};

  constructor(id, title, author, gender, description, likes, imageUrl, acl) {
    this._id = id;
    this.title = title;
    this.author = author;
    this.gender = gender;
    this.description = description;
    this.likes = likes;
    this.imageUrl = imageUrl;
    this.isLiked = false;
    this._acl = acl;
  }
}
