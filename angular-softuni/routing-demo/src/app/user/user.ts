export class User {
  public name: string;
  public height: string;
  public mass: string;
  public url: string;

  constructor(name: string, height: string, mass: string, url: string) {
    this.name = name;
    this.height = height;
    this.mass = mass;
    this.url = url;
  }
}
