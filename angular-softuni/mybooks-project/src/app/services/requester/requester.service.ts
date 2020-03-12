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

  get<T>(url: string, headers?) {
    return this.httpClient.get<T>(url, {headers: headers ? headers : this.headers});
  }

  post<T>(url: string, body, headers?) {
    return this.httpClient.post<T>(url, body, {headers: headers ? headers : this.headers});
  }

  put<T>(url: string, body, headers?) {
    return this.httpClient.put<T>(url, body, {headers: headers ? headers : this.headers});
  }
}
