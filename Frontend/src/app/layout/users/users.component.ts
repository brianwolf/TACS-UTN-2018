import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { User } from '../../shared/model/user';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  animations: [routerTransition()]
})
export class UsersComponent implements OnInit {

  users: User[];

  constructor() { }

  ngOnInit() {
    this.users = [
      new User('juan', 123, 456, 789, '10/05/2000'),
      new User('pedro', 123, 456, 789, '10/05/2001'),
      new User('maria', 123, 456, 789, '10/05/2002')
    ];
  }

}
