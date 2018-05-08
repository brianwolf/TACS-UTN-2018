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

  users: any[];
  user;
  userSelected: string;
  showInfo: boolean;

  constructor(private adminService: AdminService, private alert: AlertService) { }

  ngOnInit() {
    this.fillSelector();
  }

  fillSelector() {
    this.adminService.getUsers().subscribe(data => this.users = (<any>data));
  }

  getUser() {
    if (!this.userSelected) {
      this.alert.raise('danger', 'Debe seleccionar al usuario!');
      return;
    }
    this.adminService.getUser(this.userSelected)
      .subscribe(data => {
        this.user = data;
        this.showInfo = true;
      }, error => this.alert.raise('danger', 'ERROR: No se puede conectar con el servidor.')
      );
  }

}
