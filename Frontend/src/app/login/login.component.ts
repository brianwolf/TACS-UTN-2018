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

  constructor(public alert: AlertService, private userService: UserService, public router: Router) { }

  ngOnInit() { }

  onSubmit(form: NgForm) {
    // const body = { nick: 'lobezzzno', pass: '1234' };
    const body = { nick: form.value.nick, pass: form.value.pass };
    this.userService.login(body)
      .subscribe(
        data => {
          localStorage.setItem('currentToken', data.token);
          this.checkAdmin();
        },
        error => this.alert.raise('danger', 'ERROR: No se puede conectar con el servidor o no existe el usuario.')
      );
  }

  checkAdmin() {
    this.userService.getUserByToken()
      .subscribe(
        data => {
          localStorage.setItem('currentUserName', (<any>data).login.nick);
          for (const rol of (<any>data).roles) {
            if (rol.description === 'Administrador') {
              localStorage.setItem('currentUserRole', 'Admin');
              this.router.navigate(['dashboard']);
              return;
            }
          }
          localStorage.setItem('currentUserRole', 'User');
          this.router.navigate(['dashboard']);
        },
        error => this.alert.raise('danger', 'ERROR: No se puede obtener datos de usuario.')
      );
  }

}
