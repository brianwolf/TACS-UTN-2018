import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { AdminService } from '../../shared/services/admin.service';
import { AlertService } from '../../shared/services/alert.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styles: [' td { text-align:center } '],
  animations: [routerTransition()]
})
export class UsersComponent implements OnInit {

  user;
  users;
  showInfo: boolean;

  constructor(private adminService: AdminService, private alert: AlertService) { }

  ngOnInit() {
    this.fillSelector();
  }

  fillSelector() {
    this.adminService.getUsers().subscribe(data => this.users = data);
  }

  getUser(userSelected) {
    this.adminService.getUser(userSelected)
      .subscribe(data => {
        this.user = data;
        this.showInfo = true;
      }, error => this.alert.raise('danger', 'ERROR: No se puede conectar con el servidor.')
      );
  }

}
