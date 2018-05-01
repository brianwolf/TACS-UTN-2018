import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { Componente } from '../../componente';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent extends Componente implements OnInit {

  constructor(public translate: TranslateService, public router: Router) {
    super(translate, router);
  }

  ngOnInit() { }

}
