import { Component } from '@angular/core';
import { ComponentService } from '../../../shared/services/component.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {

  menu;

  constructor(public cs: ComponentService) { }

  toggleMenu(element) {
    this.menu = element === this.menu ? null : element;
  }

}
