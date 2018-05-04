import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { Transaction } from '../../shared/model/transaction';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  animations: [routerTransition()]
})
export class TransactionsComponent implements OnInit {

  transactions: Transaction[];

  constructor() {
    this.transactions = [
      new Transaction('BTC', 123, 456, 12),
      new Transaction('ETH', 123, 456, 12),
      new Transaction('LTC', 123, 456, 12)
    ];
  }

  ngOnInit() { }

}
