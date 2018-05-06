import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { routerTransition } from '../../router.animations';
import { AlertService } from '../../shared/services/alert.service';
import { UserService } from '../../shared/services/user.service';
import { Operation } from '../../shared/model/operation';

@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  animations: [routerTransition()]
})
export class BuyComponent implements OnInit {

  operation: Operation;

  constructor(private userService: UserService, private alert: AlertService) { }

  ngOnInit() {
    this.operation = new Operation();
  }

  onSubmit(form: NgForm) {
    this.userService.buy(this.operation)
      .subscribe(
        data => {
          this.alert.raise('success', 'Operación realizada con exito.');
          form.reset();
        },
        error => {
          this.alert.raise('danger', 'Error de conexión con el servidor');
          form.reset();
        }
      );
  }

}
