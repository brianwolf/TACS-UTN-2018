import { Component } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { AdminService } from '../../shared/services/admin.service';
import { AlertService } from '../../shared/services/alert.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styles: [` td { text-align:center } `],
  animations: [routerTransition()]
})
export class UsersComponent {

  user;
  rol;

  proccesing;

  constructor(public alertService: AlertService, public adminService: AdminService) { }

  getUser(nick) {
    this.adminService.getUser(nick).subscribe(
      data => {
        this.user = data;
        this.rol = this.getUserRol(this.user.user.roles);
        this.proccesing = false;
      },
      error => this.alertService.error(error.error.message)
    );
  }

  getUserRol(roles) {
    for (const rol of roles) {
      if (rol.description === 'Administrador') {
        return 'Administrador';
      }
    }
    return 'Usuario';
  }

  convertAdmin(nick) {
    this.proccesing = true;
    this.adminService.convertAdmin(nick).subscribe(
      success => this.getUser(nick),
      error => this.alertService.error(error.error.message)
    );
  }

}
