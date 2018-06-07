import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { routerTransition } from '../../router.animations';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './user.component.html',
  styles: ['mat-form-field { width: 100% }'],
  animations: [routerTransition()]
})
export class UserComponent implements OnInit {

  user;

  constructor(private userService: UserService, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.getUser();
  }

  onSubmit(form: NgForm) {
    if (form.value.newPass !== form.value.confirmedNewPass) {
      this.snackBar.open('Las contraseñas nuevas no coinciden.', 'x', { panelClass: 'alert-danger' });
      form.controls['newPass'].reset();
      form.controls['confirmedNewPass'].reset();
    } else {
      console.log(this.user);
    }
    // this.userService.putUser(this.user).subscribe();
  }

  getUser() {
    this.userService.getUser().subscribe(
      data => this.user = data,
      error => this.snackBar.open(error.error.message, 'x', { panelClass: 'alert-danger' })
    );
  }

}
