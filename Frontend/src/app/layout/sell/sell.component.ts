import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { routerTransition } from '../../router.animations';
import { AlertService } from '../../shared/services/alert.service';
import { UserService } from '../../shared/services/user.service';
import { DollarBalanceComponent } from '../components/dollar-balance/dollar-balance.component';

@Component({
  selector: 'app-sell',
  templateUrl: './sell.component.html',
  animations: [routerTransition()]
})
export class SellComponent implements OnInit {

  @ViewChild(DollarBalanceComponent)
  private saldo: DollarBalanceComponent;

  sellForm = new FormGroup({
    coin: new FormControl(),
    quantity: new FormControl(0, [Validators.required, Validators.min(0.00000001)])
  });

  coins;

  constructor(public alertService: AlertService, private userService: UserService) { }

  ngOnInit() {
    this.reset();
  }

  onSubmit() {
    if (this.sellForm.value.quantity > this.sellForm.value.coin.amount) {
      this.alertService.warning('La cantidad ingresada es mayor a la disponible.');
      this.resetQuantity();
    } else {
      const body = { ticker: this.sellForm.value.coin.coin.ticker, amount: this.sellForm.value.quantity };
      this.userService.sell(body).subscribe(
        success => this.alertService.success(`Se vendió ${body.amount} ${body.ticker}.`),
        error => this.alertService.error(error.error.message),
        () => this.reset()
      );
    }
  }

  getWallet() {
    this.userService.getWallet().subscribe((data: any) => {
      this.coins = data.coinAmounts;
      this.saldo.getWallet();
    });
  }

  reset() {
    this.coins = null;
    this.sellForm.reset();
    this.getWallet();
  }

  resetQuantity() {
    this.sellForm.controls['quantity'].reset();
  }

}
