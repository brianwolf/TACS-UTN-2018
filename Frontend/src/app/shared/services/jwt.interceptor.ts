import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';


@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private router: Router) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    request = request.clone({ setHeaders: { 'Content-Type': 'application/json' } });
    const currentToken = localStorage.getItem('currentToken');
    if (currentToken) {
      request = request.clone({ setHeaders: { token: currentToken } });
      // request = request.clone({ setHeaders: { Authorization: `Bearer ${currentUser.token}` } });
    }
    console.log(request);
    return next.handle(request).pipe(tap(
      (event: HttpEvent<any>) => {
        if (event instanceof HttpResponse) { }
      }, (err: any) => {
        if (err instanceof HttpErrorResponse) {
          if (err.status === 401) {
            localStorage.removeItem('currentToken');
            localStorage.removeItem('currentUserName');
            localStorage.removeItem('currentUserRole');
            this.router.navigate(['login']);
          }
        }
      }));
  }
}
