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
  public imageUrl: string;

  constructor(id, title, author, descriptionSort, likes, imageUrl) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.descriptionShort = descriptionSort;
    this.likes = likes;
    this.imageUrl = imageUrl;
  }
}

export class Book {
  public id: string;
  public title: string;
  public author: IAuthor;
  public descriptionShort: string;
  public descriptionFull: IDescription;
  public likes: number;
  public imageUrl: string;

  constructor(id: string, title, author, descriptionSort, descriptionFull, likes, imageUrl) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.descriptionShort = descriptionSort;
    this.descriptionFull = descriptionFull;
    this.likes = likes;
    this.imageUrl = imageUrl;
  }
}
