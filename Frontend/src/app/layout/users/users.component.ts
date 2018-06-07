import { Component, OnInit } from '@angular/core';
import { FormControl, NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { routerTransition } from '../../router.animations';
import { AdminService } from '../../shared/services/admin.service';

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

  constructor(public adminService: AdminService, public snackBar: MatSnackBar) { }

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
      this.snackBar.open('El usuario seleccionado no existe.', 'x', { panelClass: 'alert-warning' });
    } else {
      this.adminService.getUser(nick).subscribe(
        data => {
          this.user = data;
          this.rol = this.getUserRol(this.user.user.roles);
          this.proccesing = false;
        },
        error => this.snackBar.open(error.error.message, 'x', { panelClass: 'alert-danger' })
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
      error => this.snackBar.open(error.error.message, 'x', { panelClass: 'alert-danger' })
    );
  }

}
