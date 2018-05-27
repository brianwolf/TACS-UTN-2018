import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { routerTransition } from '../../router.animations';
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

  constructor(private userService: UserService, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.reset();
  }

  onSubmit(form: NgForm) {
    if (form.value.amount <= 0) {
      this.snackBar.open('Debe ingresar un número positivo.', 'x', { panelClass: 'alert-warning' });
    } else if (form.value.amount > this.coinSelected.amount) {
      this.snackBar.open('La cantidad ingresada es mayor a la disponible.', 'x', { panelClass: 'alert-warning' });
    } else {
      const body = { ticker: form.value.coin.coin.ticker, amount: form.value.amount };
      this.userService.sell(body)
        .subscribe(
          data => this.snackBar.open(`Se vendió ${body.amount} ${body.ticker}.`, 'x'),
          error => this.snackBar.open('ERROR: No se pudo realizar la operación.', 'x', { panelClass: 'alert-danger' })
        );
    }
    form.reset();
    this.reset();
  }

  getWallet() {
    this.userService.getWallet().subscribe((data: any) => {
      this.coins = data.coinAmounts;
      this.saldoUSD = data.dolarAmount;
    });
  }

  reset() {
    this.coinSelected = null;
    this.getWallet();
  }

}
