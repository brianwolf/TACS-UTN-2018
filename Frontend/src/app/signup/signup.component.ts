import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../router.animations';
import { NgForm } from '@angular/forms';
import { User } from '../shared/model/user';
import { AlertService } from '../shared/services/alert.service';
import { UserService } from '../shared/services/user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
  animations: [routerTransition()]
})
export class SignupComponent implements OnInit {

  user: User;
  confirmPassword: string;

  constructor(public alert: AlertService, private userService: UserService) { }

  ngOnInit() {
    this.resetForm();
  }

  resetForm(form?: NgForm) {
    if (form != null) {
      form.reset();
    }
    this.user = {
      nick: null,
      pass: null
    };
  }

  onSubmit(form: NgForm) {
    // console.log(this.user);
    if (this.user.nick !== this.confirmPassword) {
      this.alert.raise('danger', 'Las contraseñas no coinciden');
      return;
    }
    // this.alert.raise('dark', 'Usuario Registrado con exito');
    this.userService
      .signup(this.user)
      .subscribe(
        data => {
          this.resetForm(form);
          this.alert.raise('dark', 'Usuario Registrado con exito');
        },
        error => {
          this.alert.raise('danger', error);
        });
    // .subscribe((data: any) => {
    //   if (data.Succeeded === true) {
    //     this.resetForm(form);
    //     this.alertService.success('Usuario Registrado con exito');
    //   } else {
    //     this.toastr.error(data.Errors[0]);
    //   }
    // });
  }

}
