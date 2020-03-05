import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RequesterService {
  private readonly appKey = 'kid_S10Q4H5NU';
  private readonly appSecret = 'b51ec6dc96964599b69eeb78e50cdb3c';
  private url = `https://baas.kinvey.com/user/${this.appKey}/login`;
  private headers = {
    'Content-type': 'application/json',
    Authorization: 'Basic ' + btoa(`${this.appKey}:${this.appSecret}`)
  };

  constructor(private httpClient: HttpClient) {
  }

  get() {

  }

  post(body) {
    return this.httpClient.post(this.url, body, {headers: this.headers});
  }
}
