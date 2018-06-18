import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material';

@Injectable()
export class AlertService {

  TIME = 3000;

  constructor(public snackBar: MatSnackBar) { }

  success(message, time?) {
    this.snackBar.open(message, 'x', { panelClass: 'alert-success', duration: this.time(time) });
  }

  warning(message, time?) {
    this.snackBar.open(message, 'x', { panelClass: 'alert-warning', duration: this.time(time) });
  }

  error(message, time?) {
    this.snackBar.open(message, 'x', { panelClass: 'alert-danger', duration: this.time(time) });
  }

  time(value) {
    if (!value) {
      value = this.TIME;
    }
    return value;
  }

}
