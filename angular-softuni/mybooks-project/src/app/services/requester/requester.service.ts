import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RequesterService {
  private readonly appKey = 'kid_S10Q4H5NU';
  private readonly appSecret = 'b51ec6dc96964599b69eeb78e50cdb3c';
  private headers = {
    'Content-type': 'application/json',
    Authorization: 'Basic ' + btoa(`${this.appKey}:${this.appSecret}`)
  };

  constructor(private httpClient: HttpClient) {
  }

  get(url: string, headers?) {
    return this.httpClient.get(url, {headers: headers ? headers : this.headers});
  }

  post(url: string, body, headers?) {
    return this.httpClient.post(url, body, {headers: headers ? headers : this.headers});
  }

  put(url: string, body, headers?) {
    console.log(url, body, headers);
    return this.httpClient.put(url, body, {headers: headers ? headers : this.headers});
  }
}
