import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { routerTransition } from '../router.animations';
import { UserService } from '../shared/services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
  animations: [routerTransition()]
})
export class SignupComponent implements OnInit {

  constructor(private userService: UserService, public router: Router, public snackBar: MatSnackBar) { }

  ngOnInit() { }

  onSubmit(form: NgForm) {
    if (form.value.pass !== form.value.confirmPass) {
      this.snackBar.open('Las contraseñas no coinciden.', 'x', { panelClass: 'alert-warning' });
      return;
    }
    const body = { login: { nick: form.value.nick, pass: form.value.pass } };
    form.controls['pass'].reset();
    form.controls['confirmPass'].reset();
    this.userService.signup(body)
      .subscribe(
        data => {
          form.reset();
          this.snackBar.open('Usuario Registrado con exito.', 'x');
          this.router.navigate(['login']);
        },
        error => this.snackBar.open('ERROR: No se puede conectar con el servidor.', 'x', { panelClass: 'alert-danger' })
      );
  }

}
