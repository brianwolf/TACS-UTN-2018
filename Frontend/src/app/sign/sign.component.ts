import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../router.animations';

@Component({
  selector: 'app-sign',
  templateUrl: './sign.component.html',
  styleUrls: ['./sign.component.scss'],
  animations: [routerTransition()]
})
export class SignComponent implements OnInit {

  constructor() { }

  ngOnInit() { }

}
