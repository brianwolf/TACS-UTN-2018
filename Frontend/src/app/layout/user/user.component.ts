import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { routerTransition } from '../../router.animations';
import { AlertService } from '../../shared/services/alert.service';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './user.component.html',
  styles: ['mat-form-field { width: 100% }'],
  animations: [routerTransition()]
})
export class UserComponent implements OnInit {

  newUser;
  oldUser;

  constructor(public alertService: AlertService, private userService: UserService) { }

  ngOnInit() {
    this.getUser();
  }

  getUser() {
    this.userService.getUser().subscribe(
      data => {
        this.newUser = data;
        this.oldUser = JSON.parse(JSON.stringify(data));
      },
      error => this.alertService.error(error.error.message)
    );
  }

  onSubmit(form: NgForm) {
    if (form.value.newPass !== form.value.confirmedNewPass) {
      this.alertService.warning('Las contraseñas nuevas no coinciden.');
    } else {
      this.oldUser.login.pass = form.value.pass;
      this.newUser.login.pass = form.value.newPass;
      const body = { oldUser: this.oldUser, newUser: this.newUser };
      this.userService.modify(body).subscribe(
        success => this.alertService.success('Usuario Actualizado con exito.'),
        error => this.alertService.error(error.error.message)
      );
    }
    form.controls['pass'].reset();
    form.controls['newPass'].reset();
    form.controls['confirmedNewPass'].reset();
  }

}
