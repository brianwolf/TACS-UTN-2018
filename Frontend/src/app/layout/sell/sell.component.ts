import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { routerTransition } from '../../router.animations';
import { AlertService } from '../../shared/services/alert.service';
import { Operation } from '../../shared/model/operation';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-sell',
  templateUrl: './sell.component.html',
  animations: [routerTransition()]
})
export class SellComponent implements OnInit {

  operation: Operation;

  constructor(private userService: UserService, private alert: AlertService) { }

  ngOnInit() {
    this.operation = new Operation();
  }

  onSubmit(form: NgForm) {
    console.log(form.value);
    this.userService.sell(this.operation)
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
