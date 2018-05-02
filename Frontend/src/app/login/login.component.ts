import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { routerTransition } from '../router.animations';
import { AlertService } from '../shared/services/alert.service';
import { UserService } from '../shared/services/user.service';
import { User } from '../shared/model/user';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  animations: [routerTransition()]
})
export class LoginComponent implements OnInit {

  constructor(public alert: AlertService, public router: Router, private userService: UserService) { }

  user = new User;

  ngOnInit() { }

  onSubmit(form: NgForm) {
    console.log(form.value);
    console.log(this.user);
    localStorage.setItem('currentUser', JSON.stringify(this.user));
    this.router.navigate(['dashboard']);
    // this.userService.login(this.user)
    //   .subscribe(
    //     data => {
    //       this.router.navigate(['dashboard']);
    //     },
    //     error => {
    //       // this.alert.raise('danger', error.message);
    //       this.alert.raise('danger', 'Error de conexión con el servidor');
    //     });
  }
}
