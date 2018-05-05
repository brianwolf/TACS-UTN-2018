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

  constructor(public alert: AlertService, private userService: UserService) { }

  ngOnInit() {
    this.user = new User();
  }

  onSubmit(form: NgForm) {
    if (this.user.pass !== this.user.confirmPassword) {
      this.alert.raise('danger', 'Las contraseñas no coinciden');
      return;
    }
    this.userService
      .signup(this.user)
      .subscribe(
        data => {
          form.reset();
          this.alert.raise('dark', 'Usuario Registrado con exito.');
        },
        error => {
          this.alert.raise('danger', 'Error de conexión con el servidor!');
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
