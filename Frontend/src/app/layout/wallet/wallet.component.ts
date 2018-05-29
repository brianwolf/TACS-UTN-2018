import { Component, ViewChild, OnInit } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { routerTransition } from '../../router.animations';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-wallet',
  templateUrl: './wallet.component.html',
  styleUrls: ['../layout.component.scss'],
  animations: [routerTransition()]
})
export class WalletComponent implements OnInit {

  displayedColumns = ['ticker', 'name', 'amount', 'valueInDollars', 'total'];
  wallet;
  coins = new MatTableDataSource<any>();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getWallet();
  }

  getWallet() {
    this.userService.getWallet().subscribe(data => {
      this.wallet = data;
      this.coins.data = this.wallet.coinAmounts;
      this.initTableFunctions();
    });
  }

  applyFilter(filterValue: string) {
    this.coins.filter = filterValue;
  }

  initTableFunctions() {
    /* Filtering */
    this.coins.filterPredicate = (coin, filter: string) => {
      const accumulator = (currentTerm, property) => {
        switch (property) {
          case 'coin': return currentTerm + coin.coin.ticker
            + coin.coin.name
            + coin.coin.valueInDollars;
          default: return currentTerm + coin[property];
        }
      };
      const dataStr = Object.keys(coin).reduce(accumulator, '').toLowerCase();
      const transformedFilter = filter.trim().toLowerCase();
      return dataStr.indexOf(transformedFilter) !== -1;
    };
    /* Sorting */
    this.coins.sortingDataAccessor = (coin, property) => {
      switch (property) {
        case 'ticker': return coin.coin.ticker;
        case 'name': return coin.coin.name;
        case 'valueInDollars': return coin.coin.valueInDollars;
        case 'total': return coin.coin.valueInDollars * coin.amount;
        default: return coin[property];
      }
    };
    this.coins.sort = this.sort;
    /* Paginator */
    this.coins.paginator = this.paginator;
  }

}
