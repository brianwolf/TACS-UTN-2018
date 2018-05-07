import { Injectable } from '@angular/core';

@Injectable()
export class AlertService {

  type: string;
  message: string;
  status = false;

  raise(type: string, message: string, time?: number) {
    this.type = type;
    this.message = message;
    this.status = true;
    if (time == null) {
      time = 3000;
    }
    setTimeout(() => this.status = false, time);
  }

}
