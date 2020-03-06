interface IAuthor {
  fullName: string;
  avatarLink: string;
}

interface ICharacter {
  name: string;
  age: number;
}

export class Book {
  public id: string;
  public title: string;
  public author: IAuthor;
  public description: string;
  public likes: number;
  public isLiked: boolean;
  public imageUrl: string;
  public gender: string;

  constructor(id, title, author, gender, description, likes, imageUrl) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.gender = gender;
    this.description = description;
    this.likes = likes;
    this.imageUrl = imageUrl;
    this.isLiked = false;
  }
}
