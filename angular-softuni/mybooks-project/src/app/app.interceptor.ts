import {Injectable} from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpHeaders
} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class AppInterceptor implements HttpInterceptor {
  private readonly appKey = 'kid_S10Q4H5NU';
  private readonly appSecret = 'b51ec6dc96964599b69eeb78e50cdb3c';

  constructor() {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (sessionStorage.getItem('authtoken')) {
      request = request.clone({
        headers: new HttpHeaders({
          Authorization: `Kinvey ${sessionStorage.getItem('authtoken')}`
        })
      });
    } else {
      request = request.clone({
        headers: new HttpHeaders({
          Authorization: `Basic ${btoa(this.appKey + ':' + this.appSecret)}`
        })
      });
    }

    return next.handle(request);
  }
}
