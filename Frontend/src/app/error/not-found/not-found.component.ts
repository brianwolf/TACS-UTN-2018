import { Component } from '@angular/core';
import { routerTransition } from '../../router.animations';

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  styleUrls: ['../error.component.scss'],
  animations: [routerTransition()]
})
export class NotFoundComponent {

  constructor() { }

}
