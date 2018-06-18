import { Component, OnInit } from '@angular/core';
import { FormControl, NgForm } from '@angular/forms';
import { routerTransition } from '../../router.animations';
import { AdminService } from '../../shared/services/admin.service';
import { AlertService } from '../../shared/services/alert.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styles: [`
  td { text-align:center }
  mat-form-field { width: 100% }
  `],
  animations: [routerTransition()]
})
export class UsersComponent implements OnInit {

  disabled = true;

  user;
  rol;

  proccesing;

  constructor(public alertService: AlertService, public adminService: AdminService) { }

  ngOnInit() {
    this.adminService.fillNicksSelector();
  }

  activateButton() {
    this.user = null;
    this.disabled = false;
  }

  getUser(nick) {
    this.disabled = true;
    if (!this.adminService.nicks.includes(nick)) {
      this.alertService.warning('El usuario seleccionado no existe.');
    } else {
      this.adminService.getUser(nick).subscribe(
        data => {
          this.user = data;
          this.rol = this.getUserRol(this.user.user.roles);
          this.proccesing = false;
        },
        error => this.alertService.error(error.error.message)
      );

    }
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
      () => this.getUser(nick),
      error => this.alertService.error(error.error.message)
    );
  }

}
