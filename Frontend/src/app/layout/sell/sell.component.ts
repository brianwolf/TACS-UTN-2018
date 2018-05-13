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

  constructor(private userService: UserService, private alert: AlertService) { }

  ngOnInit() {
    this.reset();
  }

  onSubmit(form: NgForm) {
    if (form.value.amount <= 0) {
      this.alert.raise('warning', 'Debe ingresar un número positivo.');
    } else if (form.value.amount > this.coinSelected.amount) {
      this.alert.raise('warning', 'La cantidad ingresada es mayor a la disponible.');
    } else {
    const body = { ticker: form.value.coin.coin.ticker, amount: form.value.amount };
    this.userService.sell(body)
      .subscribe(
        data => this.alert.raise('success', 'Operación realizada con exito.')
        ,
        error => this.alert.raise('danger', 'Chequeé que la cantidad no supere el máximo disponible.', 5000)
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
