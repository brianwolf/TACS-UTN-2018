import { Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { routerTransition } from '../../router.animations';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['../layout.component.scss'],
  animations: [routerTransition()]
})
export class TransactionsComponent implements OnInit {

  displayedColumns = ['date', 'description', 'ticker', 'name', 'amount', 'quoteTimeSold', 'quoteTimeNow', 'quoteDifference'];
  operations = new MatTableDataSource<any>();
  coins;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getAllCoins();
  }

  getAllCoins() {
    this.userService.getAllCoins().subscribe(
      data => {
        this.coins = data;
        this.getTransactions();
      });
    // setTimeout(() => this.getAllCoins(), 10000);
  }

  getActualQuote(operation) {
    return this.coins.find(coin => coin.ticker === operation.coinAmount.coin.ticker).valueInDollars;
  }

  getDiff(operation) {
    return (this.getActualQuote(operation) - operation.quoteTimeSold) / operation.quoteTimeSold;
  }

  getTransactions() {
    this.userService.getTransactions()
      .subscribe(
        data => {
          const transactions = data;
          this.operations.data = Array.from(transactions[0].operations[0]);
          for (const i in transactions) {
            for (const j in transactions[i].operations) {
              this.operations.data.push(transactions[i].operations[j]);
            }
          }
          this.initTableFunctions();
        }
      );
  }

  applyFilter(filterValue: string) {
    this.operations.filter = filterValue;
  }

  initTableFunctions() {
    /* Filtering */
    this.operations.filterPredicate = (operation, filter: string) => {
      const accumulator = (currentTerm, property) => {
        switch (property) {
          case 'coinAmount': return currentTerm + operation.coinAmount.coin.ticker
            + operation.coinAmount.coin.name
            + operation.coinAmount.amount;
          default: return currentTerm + operation[property];
        }
      };
      const dataStr = Object.keys(operation).reduce(accumulator, '').toLowerCase();
      const transformedFilter = filter.trim().toLowerCase();
      return dataStr.indexOf(transformedFilter) !== -1;
    };
    /* Sorting */
    this.operations.sortingDataAccessor = (operation, property) => {
      switch (property) {
        case 'ticker': return operation.coinAmount.coin.ticker;
        case 'name': return operation.coinAmount.coin.name;
        case 'amount': return operation.coinAmount.amount;
        case 'quoteTimeNow': return this.getActualQuote(operation);
        case 'quoteDifference': return this.getDiff(operation);
        default: return operation[property];
      }
    };
    this.operations.sort = this.sort;
    /* Paginator */
    this.operations.paginator = this.paginator;
  }

}
