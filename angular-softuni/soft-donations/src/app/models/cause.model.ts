export class Cause {
  public id: string;
  public title: string;
  public description: string;
  public imageUrl: string;
  public moneyNeeded: number;
  public currentMoney: number;

  constructor(id: string, title: string, description: string, imageUrl: string, moneyNeeded: number, currentMoney: number) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.imageUrl = imageUrl;
    this.moneyNeeded = moneyNeeded;
    this.currentMoney = currentMoney;
  }
}
