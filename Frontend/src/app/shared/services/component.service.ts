import { Injectable } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { UserService } from '../../shared/services/user.service';

@Injectable()
export class ComponentService {

  sidebarActived;
  pushRightClass = 'push-right';

  constructor(public translate: TranslateService, public router: Router, public userService: UserService) {

    this.translate.addLangs(['en', 'es']);
    this.translate.setDefaultLang('es');
    const browserLang = this.translate.getBrowserLang();
    this.translate.use(browserLang.match(/en|es/) ? browserLang : 'es');

    this.router.events.subscribe(val => {
      if (val instanceof NavigationEnd) {
        this.hideSidebar();
      }
    });
  }

  nick() {
    return localStorage.getItem('currentUserName');
  }

  role() {
    return localStorage.getItem('currentUserRole');
  }

  isSidebarToggled(): boolean {
    return this.sidebarActived;
  }

  showSidebar() {
    this.sidebarActived = true;
  }

  hideSidebar() {
    this.sidebarActived = false;
  }

  toggleSidebar() {
    this.sidebarActived = !this.sidebarActived;
  }

  rltAndLtr() {
    document.querySelector('body').classList.toggle('rtl');
  }

  changeLang(language: string) {
    this.translate.use(language);
  }

  logout() {
    this.userService.logout().subscribe(
      () => {
        localStorage.removeItem('currentToken');
        localStorage.removeItem('currentUserName');
        localStorage.removeItem('currentUserRole');
      }
    );
  }

}
