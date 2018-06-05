import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { routerTransition } from '../../router.animations';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-relog',
  templateUrl: './relog.component.html',
  styleUrls: ['../sign.component.scss'],
  animations: [routerTransition()]
})
export class RelogComponent implements OnInit {

  loading = false;

  constructor(private userService: UserService, public router: Router, public snackBar: MatSnackBar) { }

  ngOnInit() { }

  onSubmit(form: NgForm) {
    this.loading = true;
    const body = { nick: form.value.nick};
    form.controls['nick'].reset();
    this.userService.relog(body)
      .subscribe(
        error => {
          this.snackBar
            .open('ERROR: No se puede conectar con el servidor o credenciales incorrectas.', 'v', { panelClass: 'alert-danger' });
          this.loading = false;
        }
      );
  }

}
