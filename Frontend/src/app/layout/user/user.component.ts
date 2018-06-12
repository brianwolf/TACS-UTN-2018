import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { routerTransition } from '../../router.animations';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './user.component.html',
  styles: ['mat-form-field { width: 100% }'],
  animations: [routerTransition()]
})
export class UserComponent implements OnInit {

  user;
  loading;

  constructor(private userService: UserService, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.getUser();
  }

  getUser() {
    this.userService.getUser().subscribe(
      data => this.user = data,
      error => this.snackBar.open(error.error.message, 'x', { panelClass: 'alert-danger' })
    );
  }

  onSubmit(form: NgForm) {
    if (form.value.newPass !== form.value.confirmedNewPass) {
      this.snackBar.open('Las contraseñas nuevas no coinciden.', 'x', { panelClass: 'alert-warning' });
    } else {
      this.loading = true;
      const oldUser = this.user;
      const newUser = this.user;
      oldUser.login.pass = form.value.pass;
      newUser.login.pass = form.value.newPass;
      const body = { oldUser: oldUser, newUser: newUser };
      this.userService.signup(body).subscribe(
        data => this.snackBar.open('Usuario Actualizado con exito.', 'x'),
        error => {
          this.snackBar.open(error.error.message, 'x', { panelClass: 'alert-danger' });
          this.loading = false;
        },
        () => this.loading = false
      );
      form.controls['pass'].reset();
      form.controls['newPass'].reset();
      form.controls['confirmedNewPass'].reset();
    }
  }

}
