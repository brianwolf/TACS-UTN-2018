import { Component, OnInit } from '@angular/core';
import { ComponentService } from '../../../shared/services/component.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(public cs: ComponentService) { }

  ngOnInit() { }

}
