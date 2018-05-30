import { Component, OnInit } from '@angular/core';
import { ComponentService } from '../../../shared/services/component.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {

  menu;

  constructor(public cs: ComponentService) { }

  ngOnInit() { }

  toggleMenu(element) {
    this.menu = element === this.menu ? null : element;
  }

}
