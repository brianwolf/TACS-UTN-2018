import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';

@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  styleUrls: ['./buy.component.scss'],
  animations: [routerTransition()]
})
export class BuyComponent implements OnInit {

  constructor() { }

  ngOnInit() { }

}
