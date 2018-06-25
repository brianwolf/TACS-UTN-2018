import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { routerTransition } from '../../router.animations';
import { AlertService } from '../../shared/services/alert.service';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  animations: [routerTransition()]
})
export class DepositComponent {

  constructor(public alertService: AlertService, private userService: UserService) { }

  onSubmit(form: NgForm) {
    if (form.value.amount < 100) {
      this.alertService.warning('El monto mínimo es de u$s 100.-');
    } else {
      const body = { number: form.value.ticket, amount: form.value.amount };
      this.userService.deposit(body)
        .subscribe(
          success => this.alertService.success('El estado de su depósito se verificará en las próximas 24 horas.'),
          error => this.alertService.error(error.error.message),
      );
    }
    form.reset();
  }

}
