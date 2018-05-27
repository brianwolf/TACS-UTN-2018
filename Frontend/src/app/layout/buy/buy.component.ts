import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { routerTransition } from '../../router.animations';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  animations: [routerTransition()]
})
export class BuyComponent implements OnInit {

  saldoUSD;
  coins;

  constructor(private userService: UserService, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.reset();
  }

  onSubmit(form: NgForm) {
    if (form.value.amount <= 0) {
      this.snackBar.open('Debe ingresar un número positivo.', 'x', { panelClass: 'alert-warning' });
    } else if (form.value.amount * form.value.coin.valueInDollars > this.saldoUSD) {
      this.snackBar.open('No tiene saldo suficiente en u$s para realizar la transacción.', 'x', { panelClass: 'alert-warning' });
    } else {
      const body = { ticker: form.value.coin.ticker, amount: form.value.amount };
      this.userService.buy(body)
        .subscribe(
          data => this.snackBar.open(`Se compró ${body.amount} ${body.ticker}.`, 'x'),
          error => this.snackBar.open('ERROR: No se pudo realizar la operación.', 'x', { panelClass: 'alert-danger' })
        );
    }
    this.reset();
    form.reset();
  }

  fillSelector() {
    this.userService.getAllCoins().subscribe(data => this.coins = data);
  }

  getSaldoUSD() {
    this.userService.getWallet().subscribe((data: any) => this.saldoUSD = data.dolarAmount);
  }

  reset() {
    this.coins = null;
    this.fillSelector();
    this.getSaldoUSD();
  }

}
