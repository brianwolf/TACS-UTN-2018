import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { routerTransition } from '../../router.animations';
import { AlertService } from '../../shared/services/alert.service';
import { OperationService } from '../../shared/services/operation.service';
import { Operation } from '../../shared/model/operation';

@Component({
  selector: 'app-sell',
  templateUrl: './sell.component.html',
  animations: [routerTransition()]
})
export class SellComponent implements OnInit {

  operation: Operation;

  constructor(private operationService: OperationService, private alert: AlertService) { }

  ngOnInit() {
    this.operation = new Operation();
  }

  onSubmit(form: NgForm) {
    console.log(form.value);
    this.operationService.sell(this.operation)
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
