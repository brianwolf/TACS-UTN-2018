import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material';

@Injectable()
export class AlertService {

  TIME = 3000;
  ERROR_MESSAGE = 'Ha ocurrido un error.';

  constructor(public snackBar: MatSnackBar) { }

  success(message, time?) {
    this.snackBar.open(message, 'x', { panelClass: 'success', duration: this.time(time) });
  }

  warning(message, time?) {
    this.snackBar.open(message, 'x', { panelClass: 'warning', duration: this.time(time) });
  }

  error(message, time?) {
    this.snackBar.open(this.errorMessage(message), 'x', { panelClass: 'danger', duration: this.time(time) });
  }

  time(value) {
    return value ? value : this.TIME;
  }

  errorMessage(message) {
    return message ? message : this.ERROR_MESSAGE;
  }

}
