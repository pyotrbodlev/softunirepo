interface IAuthor {
  fullName: string;
  avatarLink: string;
}

interface ICharacter {
  name: string;
  age: number;
}

interface IDescription {
  mainCharacters: ICharacter[];
  plot: string;
}

export class BookShortInfo {
  public id: string;
  public title: string;
  public author: IAuthor;
  public descriptionShort: string;
  public likes: number;
  public isLiked: boolean;
  public imageUrl: string;

  constructor(id, title, author, descriptionSort, likes, imageUrl) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.descriptionShort = descriptionSort;
    this.likes = likes;
    this.imageUrl = imageUrl;
    this.isLiked = false;
  }
}

export class Book extends BookShortInfo {
  public descriptionFull: IDescription;

  constructor(id, title, author, descriptionSort, descriptionFull, likes, imageUrl) {
    super(id, title, author, descriptionSort, likes, imageUrl);
    this.descriptionFull = descriptionFull;
  }
}
