import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { routerTransition } from '../../router.animations';
import { AlertService } from '../../shared/services/alert.service';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  animations: [routerTransition()]
})
export class BuyComponent implements OnInit {

  coinSelected;
  saldoUSD;
  coins;

  constructor(private userService: UserService, private alert: AlertService) { }

  ngOnInit() {
    this.reset();
  }

  onSubmit(form: NgForm) {
    if (form.value.amount <= 0) {
      this.alert.raise('warning', 'Debe ingresar un número positivo.');
    } else if (form.value.amount * this.coinSelected.valueInDollars > this.saldoUSD) {
      this.alert.raise('warning', 'No tiene saldo suficiente en u$s para realizar la transacción.');
    } else {
      const body = { ticker: form.value.coin.ticker, amount: form.value.amount };
      this.userService.buy(body)
        .subscribe(
          data => this.alert.raise('success', `Se compró ${body.amount} ${body.ticker}.`)
          ,
          error => this.alert.raise('danger', 'Chequeé si tiene fondos suficientes.', 5000)
        );
    }
    form.reset();
    this.reset();
  }

  fillSelector() {
    this.userService.getAllCoins().subscribe(data => this.coins = data);
  }

  getSaldoUSD() {
    this.userService.getWallet().subscribe((data: any) => this.saldoUSD = data.dolarAmount);
  }

  reset() {
    this.fillSelector();
    this.getSaldoUSD();
    this.coinSelected = null;
  }

}
