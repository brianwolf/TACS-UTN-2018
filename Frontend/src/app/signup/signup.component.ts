import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { routerTransition } from '../router.animations';
import { AlertService } from '../shared/services/alert.service';
import { UserService } from '../shared/services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
  animations: [routerTransition()]
})
export class SignupComponent implements OnInit {

  constructor(private alert: AlertService, private userService: UserService, public router: Router) { }

  ngOnInit() { }

  onSubmit(form: NgForm) {
    if (form.value.pass !== form.value.confirmPass) {
      this.alert.raise('danger', 'Las contraseñas no coinciden');
      return;
    }
    const body = { login: { nick: form.value.nick, pass: form.value.pass } };
    this.userService.signup(body)
      .subscribe(
        data => {
          form.reset();
          this.alert.raise('success', 'Usuario Registrado con exito.');
          this.router.navigate(['login']);
        },
        error => this.alert.raise('danger', 'ERROR: No se puede conectar con el servidor.')
      );
  }

}
