import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { routerTransition } from '../../router.animations';
import { AlertService } from '../../shared/services/alert.service';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  animations: [routerTransition()]
})
export class DepositComponent {

  depositForm = new FormGroup({
    number: new FormControl(null, [Validators.required, Validators.min(1)]),
    amount: new FormControl(null, [Validators.required, Validators.min(100)])
  });

  constructor(public alertService: AlertService, private userService: UserService) { }

  onSubmit() {
    this.userService.deposit(this.depositForm.value).subscribe(
      success => this.alertService.success('El estado de su depósito se verificará en las próximas 24 horas.'),
      error => this.alertService.error(error.error.message)
    );
    this.depositForm.reset();
  }

}
