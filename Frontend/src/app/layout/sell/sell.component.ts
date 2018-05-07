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

  constructor(private userService: UserService, private alert: AlertService) { }

  ngOnInit() { }

  onSubmit(form: NgForm) {
    const body = { ticker: form.value.ticker, amount: form.value.amount };
    this.userService.sell(body)
      .subscribe(
        data => this.alert.raise('success', 'Operación realizada con exito.')
        ,
        error => this.alert.raise('danger', 'Chequeé el Ticker o si tiene fondos suficientes.', 5000)
      );
    form.reset();
  }

}
