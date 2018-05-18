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

  displayedColumns = ['ticker', 'name', 'amount', 'valueInDollars'];
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
      this.coins.paginator = this.paginator;
      this.coins.sortingDataAccessor = (coin, property) => {
        switch (property) {
          case 'ticker': return coin.coin.ticker;
          case 'name': return coin.coin.name;
          case 'valueInDollars': return coin.coin.valueInDollars;
          default: return coin[property];
        }
      };
      this.coins.sort = this.sort;
    });
  }

  applyFilter(filterValue: string) {
    this.coins.filter = filterValue.trim().toLowerCase();
  }

  rowClicked(row) {
    console.log(row);
  }

}
