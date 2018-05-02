import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';

@Component({
  selector: 'app-sell',
  templateUrl: './sell.component.html',
  styleUrls: ['./sell.component.scss'],
  animations: [routerTransition()]
})
export class SellComponent implements OnInit {

  constructor() { }

  ngOnInit() { }

}
