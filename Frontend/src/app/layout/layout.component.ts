import { Component, OnInit } from '@angular/core';
import { ComponentService } from '../shared/services/component.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

  constructor(public cs: ComponentService) { }

  ngOnInit() { }

}
