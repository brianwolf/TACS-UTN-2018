import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-relog',
  templateUrl: './relog.component.html',
  styleUrls: ['../sign.component.scss']
})
export class RelogComponent implements OnInit {

  loading = false;

  constructor(private userService: UserService, public router: Router, public snackBar: MatSnackBar) { }

  ngOnInit() { }

  onSubmit(form: NgForm) {
    this.loading = true;
    form.controls['nick'].reset();
    this.userService.relog(form.value.nick).subscribe(
      data => {
        form.reset();
        this.snackBar.open('Se le envio un mail con su nueva contraseña.', 'x');
        this.router.navigate(['login']);
      },
      error => {
        this.snackBar.open(error.error.message, 'x', { panelClass: 'alert-danger' });
        this.loading = false;
      }
    );
  }

}
