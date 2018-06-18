import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from '../../shared/services/alert.service';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['../sign.component.scss']
})
export class SignupComponent {

  loading = false;

  constructor(public alertService: AlertService, private userService: UserService, public router: Router) { }

  onSubmit(form: NgForm) {
    if (form.value.pass !== form.value.confirmPass) {
      this.alertService.warning('Las contraseñas no coinciden.');
    } else {
      this.loading = true;
      const body = { login: { nick: form.value.nick, pass: form.value.pass }, person: { email: form.value.email } };
      this.userService.signup(body).subscribe(
        data => {
          this.alertService.success('Usuario Registrado con exito.');
          this.router.navigate(['login']);
        },
        error => {
          this.alertService.error(error.error.message);
          this.loading = false;
        }
      );
    }
    form.controls['pass'].reset();
    form.controls['confirmPass'].reset();
  }

}
