import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { routerTransition } from '../../router.animations';
import { AlertService } from '../../shared/services/alert.service';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  animations: [routerTransition()]
})
export class DepositComponent implements OnInit {

  saldoUSD;

  constructor(private userService: UserService, private alert: AlertService) { }

  ngOnInit() {
    this.getWallet();
  }

  onSubmit(form: NgForm) {
    if (form.value.amount < 100) {
      this.alert.raise('warning', 'El monto mínimo es de u$s 100.-');
    } else {
      const body = { number: form.value.ticket, amount: form.value.amount };
      this.userService.deposit(body)
        .subscribe(
          success => this.alert.raise('success', 'El estado de su deposito se verificara en las proximas 24 horas.')
          ,
          (error: any) => this.alert.raise('danger', 'ERROR:' + error.message, 5000)
        );
    }
    form.reset();
  }

  getWallet() {
    this.userService.getWallet().subscribe((data: any) => this.saldoUSD = data.dolarAmount);
  }

}
