import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';

@Component({
  selector: 'app-server-error',
  templateUrl: './server-error.component.html',
  styleUrls: ['../error.scss'],
  animations: [routerTransition()]
})
export class ServerErrorComponent implements OnInit {

  constructor() { }

  ngOnInit() { }

}
