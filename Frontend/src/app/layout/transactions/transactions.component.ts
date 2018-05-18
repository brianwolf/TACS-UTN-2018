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

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getTransactions();
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
          this.operations.paginator = this.paginator;
          this.operations.sortingDataAccessor = (operation, property) => {
            switch (property) {
              case 'ticker': return operation.coinAmount.coin.ticker;
              case 'name': return operation.coinAmount.coin.name;
              case 'amount': return operation.coinAmount.amount;
              default: return operation[property];
            }
          };
          this.operations.sort = this.sort;
        }
      );
  }

  applyFilter(filterValue: string) {
    this.operations.filter = filterValue.trim().toLowerCase();
  }

  rowClicked(row) {
    console.log(row);
  }

}
