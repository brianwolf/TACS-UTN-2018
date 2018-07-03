import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
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

  buyForm = new FormGroup({
    coin: new FormControl(),
    quantity: new FormControl(0, [Validators.required, Validators.min(0.00000001)])
  });

  coins;

  constructor(public alertService: AlertService, private userService: UserService) { }

  ngOnInit() {
    this.reset();
  }

  onSubmit() {
    if (this.buyForm.value.quantity * this.buyForm.value.coin.valueInDollars > this.saldo.saldoUSD) {
      this.alertService.warning('No tiene saldo suficiente en u$s para realizar la transacción.');
      this.resetQuantity();
    } else {
      const body = { ticker: this.buyForm.value.coin.ticker, amount: this.buyForm.value.quantity };
      this.userService.buy(body).subscribe(
        success => this.alertService.success(`Se compró ${body.amount} ${body.ticker}.`),
        error => this.alertService.error(error.error.message),
        () => this.reset()
      );
    }
  }

  reset() {
    this.coins = null;
    this.buyForm.reset();
    this.fillSelector();
    this.saldo.getWallet();
  }

  fillSelector() {
    this.userService.getAllCoins().subscribe(data => this.coins = data);
  }

  resetQuantity() {
    this.buyForm.controls['quantity'].reset();
  }

  maxQuantity() {
    return Math.floor(this.saldo.saldoUSD / this.buyForm.value.coin.valueInDollars);
  }

}
