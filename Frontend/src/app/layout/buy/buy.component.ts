import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { routerTransition } from '../../router.animations';
import { AlertService } from '../../shared/services/alert.service';
import { UserService } from '../../shared/services/user.service';
import { DollarBalanceComponent } from '../components/dollar-balance/dollar-balance.component';

@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  animations: [routerTransition()]
})
export class BuyComponent implements OnInit {

  @ViewChild(DollarBalanceComponent)
  private saldo: DollarBalanceComponent;
  coinSelected;
  coins;

  constructor(public alertService: AlertService, private userService: UserService) { }

  ngOnInit() {
    this.reset();
  }

  onSubmit(form: NgForm) {
    if (form.value.amount <= 0) {
      this.alertService.warning('Debe ingresar un número positivo.');
    } else if (form.value.amount * form.value.coin.valueInDollars > this.saldo.saldoUSD) {
      this.alertService.warning('No tiene saldo suficiente en u$s para realizar la transacción.');
    } else {
      const body = { ticker: form.value.coin.ticker, amount: form.value.amount };
      this.userService.buy(body)
        .subscribe(
          data => this.alertService.success(`Se compró ${body.amount} ${body.ticker}.`),
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

  fillSelector() {
    this.userService.getAllCoins().subscribe(data => this.coins = data);
  }

  reset() {
    this.coins = null;
    this.fillSelector();
    this.saldo.getWallet();
  }

}
