import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    request = request.clone({ headers: request.headers.set('Content-Type', 'application/json') });
    console.log(request);
    const currentToken = localStorage.getItem('currentToken');
    console.log(currentToken);
    if (currentToken) {
      request = request.clone({ setHeaders: { token: currentToken } });
      // request = request.clone({ setHeaders: { Authorization: `Bearer ${currentUser.token}` } });
    }
    return next.handle(request);
  }
}
