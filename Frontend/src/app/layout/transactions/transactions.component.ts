import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  animations: [routerTransition()]
})
export class TransactionsComponent implements OnInit {

  operations: any[];

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getTransactions();
  }

  getTransactions() {
    this.userService.getTransactions()
      .subscribe(
        data => {
          const _transactions = (<any>data);
          this.operations = Array.from(_transactions[0].operations[0]);
          for (const i in _transactions) {
            for (const j in _transactions[i].operations) {
              this.operations.push(_transactions[i].operations[j]);
            }
          }
        }
      );
  }

}
