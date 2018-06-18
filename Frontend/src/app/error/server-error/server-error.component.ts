import { Component } from '@angular/core';
import { routerTransition } from '../../router.animations';

@Component({
  selector: 'app-server-error',
  templateUrl: './server-error.component.html',
  styleUrls: ['../error.component.scss'],
  animations: [routerTransition()]
})
export class ServerErrorComponent {

  constructor() { }

}
