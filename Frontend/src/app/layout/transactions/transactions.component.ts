import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { Transaction } from '../../shared/model/transaction';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  animations: [routerTransition()]
})
export class TransactionsComponent implements OnInit {

  data: any[];
  transactions: any;

  constructor(private userService: UserService) { }
  // constructor() {
  //   this.transactions = [
  //     new Transaction('BTC', 123, 456, 12),
  //     new Transaction('ETH', 123, 456, 12),
  //     new Transaction('LTC', 123, 456, 12)
  //   ];
  // }

  ngOnInit() {
    this.getTransactions();
  }

  getTransactions() {
    this.userService.getTransactions()
      .subscribe(
        data => {
          this.data = JSON.parse(data.toString());
          this.transactions = this.data[0].operations;
        },
        error => this.dataAlternativa()
      );
  }


dataAlternativa() {
  this.data = JSON.parse(`
  [
    {
      "operations": [
        {
          "date": 1525464123063,
          "description": "Compra",
          "coinAmount": {
            "coin": {
              "name": "Bitcoin",
              "ticker": "BTC",
              "valueInDollars": 9644.07
            },
            "amount": 0.05
          },
          "quoteTimeSold": 9644.07,
          "quoteTimeNow": 9644.07,
          "quoteDifference": 0.00
        }
      ],
      "dateFinal": 1525464123065,
      "dateStart": 1525464123065
    }
  ]
  `);
  this.transactions = this.data[0].operations;
}

}
