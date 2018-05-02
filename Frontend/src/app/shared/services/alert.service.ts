import { Injectable } from '@angular/core';

@Injectable()
export class AlertService {
  type: string;
  message: string;
  status = false;

  raise(type: string, message: string) {
    this.type = type;
    this.message = message;
    this.status = true;
    setTimeout(() => this.status = false, 3000);
  }

}
