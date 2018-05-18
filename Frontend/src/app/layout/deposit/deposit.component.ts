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
    if (form.value.amount <= 0) {
      this.alert.raise('warning', 'Debe ingresar un número positivo.');
      // } else {
      //   const body = { amount: form.value.amount, ticket: form.value.ticket };
      //   this.userService.deposit(body)
      //     .subscribe(
      //       data => this.alert.raise('success', 'A la brevedad le informaremos el estado de su deposito.')
      //       ,
      //       error => this.alert.raise('danger', 'ERROR: no se pudo informar el deposito.', 5000)
      //     );
    }
    form.reset();
  }

  getWallet() {
    this.userService.getWallet().subscribe((data: any) => this.saldoUSD = data.dolarAmount);
  }

}
