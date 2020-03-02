import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map, tap} from 'rxjs/operators';
import {Cause} from '../models/cause.model';

interface CauseInfo {
  _id: string;
  title: string;
  description: string;
  imageUrl: string;
  moneyNeeded: number;
  currentMoney: number;
}

@Injectable({
  providedIn: 'root'
})
export class CauseService {
  http: HttpClient;

  private appKey = 'kid_S10Q4H5NU';
  private appSecret = 'b51ec6dc96964599b69eeb78e50cdb3c';
  private authToken = '77c8f104-bd2a-4b7a-b7c5-5b86380a1d36.mSjnYUEiQNdzz54Msae2yMruhmvBzczibVVQdmFwi44=';
  private url = `https://baas.kinvey.com/appdata/${this.appKey}/`;
  private readonly headers: any;

  constructor(http: HttpClient) {
    this.http = http;
    this.headers = {Authorization: 'Kinvey ' + this.authToken};
  }

  getCauses() {
    return this.http.get(this.url + 'causes', {
      headers: this.headers
    }).pipe(
      map<Array<CauseInfo>, Array<Cause>>(causesArr => {
        return causesArr.map(c => new Cause(c._id, c.title, c.description, c.imageUrl, c.moneyNeeded, c.currentMoney));
      })
    );
  }

  getCauseById(id: string) {
    return this.http.get(this.url + 'causes/' + id, {
      headers: this.headers
    }).pipe(
      map<CauseInfo, Cause>(c => new Cause(c._id, c.title, c.description, c.imageUrl, c.moneyNeeded, c.currentMoney))
    );
  }

  donateMoney(cause: Cause, amount: number) {
    cause.currentMoney = Number(cause.currentMoney) + Number(amount);

    return this.http.put(`${this.url}causes/${cause.id}`, cause, {
      headers: this.headers
    });
  }

  createCause(causeInfo) {
    return this.http.post(this.url + 'causes', causeInfo, {
      headers: this.headers
    });
  }

  deleteCause(id) {
    return this.http.delete(this.url + 'causes/' + id, {
      headers: this.headers
    });
  }
}
