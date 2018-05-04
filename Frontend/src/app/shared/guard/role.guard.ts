import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable()
export class RoleGuard implements CanActivate {
  constructor(private router: Router) { }

  canActivate() {
    if (localStorage.getItem('currentUserRole') === 'Admin') {
      return true;
    }
    this.router.navigate(['access-denied']);
    return false;
  }
}
