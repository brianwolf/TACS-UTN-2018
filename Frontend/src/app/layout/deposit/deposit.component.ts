import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { routerTransition } from '../../router.animations';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  animations: [routerTransition()]
})
export class DepositComponent implements OnInit {

  saldoUSD;

  constructor(private userService: UserService, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.getWallet();
  }

  getWallet() {
    this.userService.getWallet().subscribe((data: any) => this.saldoUSD = data.dolarAmount);
  }

  onSubmit(form: NgForm) {
    if (form.value.amount < 100) {
      this.snackBar.open('El monto mínimo es de u$s 100.-', 'v', { panelClass: 'alert-warning' });
    } else {
      const body = { number: form.value.ticket, amount: form.value.amount };
      this.userService.deposit(body)
        .subscribe(
          success => this.snackBar.open('El estado de su depósito se verificará en las próximas 24 horas.', 'x'),
          error => this.snackBar.open(`ERROR: ${error.error.message}`, 'v', { panelClass: 'alert-danger' })
        );
    }
    form.reset();
  }

}
