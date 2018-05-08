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
          const transactions = data;
          this.operations = Array.from(transactions[0].operations[0]);
          for (const i in transactions) {
            for (const j in transactions[i].operations) {
              this.operations.push(transactions[i].operations[j]);
            }
          }
        }
      );
  }

}
