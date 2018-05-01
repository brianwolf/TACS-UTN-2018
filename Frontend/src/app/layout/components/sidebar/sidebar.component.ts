import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { Componente } from '../../componente';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent extends Componente implements OnInit {

  constructor(public translate: TranslateService, public router: Router) {
    super(translate, router);
  }
  // constructor(translate: any, router: any) {
  //   super(translate, router);
  // }


  ngOnInit() { }

}
