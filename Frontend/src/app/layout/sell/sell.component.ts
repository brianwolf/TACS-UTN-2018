import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { routerTransition } from '../../router.animations';
import { AlertService } from '../../shared/services/alert.service';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-sell',
  templateUrl: './sell.component.html',
  animations: [routerTransition()]
})
export class SellComponent implements OnInit {

  coinSelected;
  saldoUSD;
  coins;

  constructor(public alertService: AlertService, private userService: UserService) { }

  ngOnInit() {
    this.reset();
  }

  onSubmit(form: NgForm) {
    if (form.value.amount <= 0) {
      this.alertService.warning('Debe ingresar un número positivo.');
    } else if (form.value.amount > form.value.coin.amount) {
      this.alertService.warning('La cantidad ingresada es mayor a la disponible.');
    } else {
      const body = { ticker: form.value.coin.coin.ticker, amount: form.value.amount };
      this.userService.sell(body)
        .subscribe(
          data => this.alertService.success(`Se vendió ${body.amount} ${body.ticker}.`),
          error => this.alertService.error(error.error.message),
          () => {
            this.reset();
            form.reset();
            return;
          }
        );
    }
    form.controls['amount'].reset();
  }

  getWallet() {
    this.userService.getWallet().subscribe((data: any) => {
      this.coins = data.coinAmounts;
      this.saldoUSD = data.dolarAmount;
    });
  }

  reset() {
    this.coins = null;
    this.getWallet();
  }

}
