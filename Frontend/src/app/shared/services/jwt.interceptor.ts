import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    request = request.clone({ setHeaders: { 'Content-Type': 'application/json' } });
    // request = request.clone({ headers: request.headers.set('Content-Type', 'application/json') });
    const currentToken = localStorage.getItem('currentToken');
    console.log(currentToken);
    if (currentToken) {
      request = request.clone({ setHeaders: { token: currentToken } });
      // request = request.clone({ headers: request.headers.set('token', currentToken) });
      // request = request.clone({ setHeaders: { Authorization: `Bearer ${currentUser.token}` } });
    }
    console.log(request);
    return next.handle(request);
  }
}
