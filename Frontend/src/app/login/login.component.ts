import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { routerTransition } from '../router.animations';
import { AlertService } from '../shared/services/alert.service';
import { UserService } from '../shared/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  animations: [routerTransition()]
})
export class LoginComponent implements OnInit {

  loading = false;

  constructor(public alert: AlertService, private userService: UserService, public router: Router) { }

  ngOnInit() { }

  onSubmit(form: NgForm) {
    this.loading = true;
    // const body = { nick: 'lobezzzno', pass: '1234' };
    const body = { nick: form.value.nick, pass: form.value.pass };
    form.controls['pass'].reset();
    this.userService.login(body)
      .subscribe(
        data => {
          localStorage.setItem('currentToken', data.token);
          this.checkAdmin();
        },
        error => {
          this.alert.raise('danger', 'ERROR: No se puede conectar con el servidor o credenciales incorrectas.');
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
          this.alert.raise('danger', 'ERROR: No se puede obtener datos de usuario.');
          this.loading = false;
        }
      );
  }

}
