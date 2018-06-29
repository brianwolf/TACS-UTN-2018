import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from '../../shared/services/alert.service';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['../sign.component.scss']
})
export class LoginComponent {

  loading = false;

  constructor(public alertService: AlertService, private userService: UserService, public router: Router) { }

  onSubmit(form: NgForm) {
    this.loading = true;
    this.userService.login(form.value).subscribe(
      (data: any) => {
        localStorage.setItem('currentToken', data.token);
        this.checkAdmin();
      },
      error => {
        this.alertService.error(error.error.message);
        this.loading = false;
      }
    );
    form.controls['pass'].reset();
  }

  checkAdmin() {
    this.userService.getUser().subscribe(
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
        this.alertService.error(error.error.message);
        this.loading = false;
      }
    );
  }

}
