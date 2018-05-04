import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-wallet',
  templateUrl: './wallet.component.html',
  animations: [routerTransition()]
})
export class WalletComponent implements OnInit {

  data: any;
  coins: any;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getWallet();
  }

  getWallet() {
    this.userService.getWallet()
      .subscribe(
        data => {
          this.data = JSON.parse(data.toString());
          this.coins = this.data.coinAmaunts;
        },
        error => this.dataAlternativa()
      );
  }

  dataAlternativa() {
    this.data = JSON.parse(`
    {
      "dolarFinalBalance": 10110.25610753558389842510223388671875,
      "dolarAmount": 9517.7965,
      "coinAmaunts": [
        {
          "coin": {
            "name": "Bitcoin",
            "ticker": "BTC",
            "valueInDollars": 9644.07
          },
          "amount": 0.054999999888241291046142578125
        },
        {
          "coin": {
            "name": "Ethereum",
            "ticker": "ETH",
            "valueInDollars": 775.447
          },
          "amount": 0.07999999821186065673828125
        }
      ]
    }
    `);
    this.coins = this.data.coinAmaunts;
  }
}
