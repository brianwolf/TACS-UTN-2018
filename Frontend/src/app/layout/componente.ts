import { Router, NavigationEnd } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

export abstract class Componente {
  isActive = false;
  showMenu = '';
  pushRightClass = 'push-right';

  constructor(public translate: TranslateService, public router: Router) {
    this.translate.addLangs(['en', 'es']);
    this.translate.setDefaultLang('es');
    const browserLang = this.translate.getBrowserLang();
    this.translate.use(browserLang.match(/en|es/) ? browserLang : 'es');

    this.router.events.subscribe(val => {
      if (
        val instanceof NavigationEnd &&
        window.innerWidth <= 992 &&
        this.isToggled()
      ) {
        this.toggleSidebar();
      }
    });
  }

  eventCalled() {
    this.isActive = !this.isActive;
  }

  addExpandClass(element: any) {
    element === this.showMenu ?
      this.showMenu = '0' :
      this.showMenu = element;
  }

  isToggled(): boolean {
    return document.querySelector('body').classList.contains(this.pushRightClass);
  }

  toggleSidebar() {
    document.querySelector('body').classList.toggle(this.pushRightClass);
  }

  rltAndLtr() {
    document.querySelector('body').classList.toggle('rtl');
  }

  changeLang(language: string) {
    this.translate.use(language);
  }

  onLoggedout() {
    localStorage.removeItem('isLoggedin');
  }

}
