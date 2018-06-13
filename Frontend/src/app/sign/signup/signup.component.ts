import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['../sign.component.scss']
})
export class SignupComponent {

  loading = false;

  constructor(private userService: UserService, public router: Router, public snackBar: MatSnackBar) { }

  onSubmit(form: NgForm) {
    if (form.value.pass !== form.value.confirmPass) {
      this.snackBar.open('Las contraseñas no coinciden.', 'x', { panelClass: 'alert-warning' });
    } else {
      this.loading = true;
      const body = { login: { nick: form.value.nick, pass: form.value.pass }, person: { email: form.value.email } };
      this.userService.signup(body).subscribe(
        data => {
          this.snackBar.open('Usuario Registrado con exito.', 'x');
          this.router.navigate(['login']);
        },
        error => {
          this.snackBar.open(error.error.message, 'x', { panelClass: 'alert-danger' });
          this.loading = false;
        }
      );
    }
    form.controls['pass'].reset();
    form.controls['confirmPass'].reset();
  }

}
