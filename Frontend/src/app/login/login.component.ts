import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { routerTransition } from '../router.animations';
import { UserService } from '../shared/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  animations: [routerTransition()]
})
export class LoginComponent implements OnInit {

  loading = false;

  constructor(private userService: UserService, public router: Router, public snackBar: MatSnackBar) { }

  ngOnInit() { }

  onSubmit(form: NgForm) {
    this.loading = true;
    const body = { nick: form.value.nick, pass: form.value.pass };
    form.controls['pass'].reset();
    this.userService.login(body)
      .subscribe(
        data => {
          localStorage.setItem('currentToken', data.token);
          this.checkAdmin();
        },
        error => {
          this.snackBar.open('ERROR: No se puede conectar con el servidor o credenciales incorrectas.',
            'x', { panelClass: 'alert-danger' });
          this.loading = false;
        }
      );
  }

  checkAdmin() {
    this.userService.getUserByToken()
      .subscribe(
        (user: any) => {
          localStorage.setItem('currentUserName', user.login.nick);
          for (const rol of user.roles) {
            if (rol.description === 'Administrador') {
              localStorage.setItem('currentUserRole', 'Admin');
              this.router.navigate(['dashboard']);
              return;
            }
          }
          localStorage.setItem('currentUserRole', 'User');
          this.router.navigate(['dashboard']);
        },
        error => {
          this.snackBar.open('ERROR: No se puede obtener datos de usuario.',
            'x', { panelClass: 'alert-danger' });
          this.loading = false;
        }
      );
  }

}
