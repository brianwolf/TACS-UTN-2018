import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { AdminService } from '../../shared/services/admin.service';
import { AlertService } from '../../shared/services/alert.service';

@Component({
  selector: 'app-compare',
  templateUrl: './compare.component.html',
  animations: [routerTransition()]
})
export class CompareComponent implements OnInit {

  users: any[];
  winnerUser: any;
  user1: string;
  user2: string;

  constructor(private adminService: AdminService, private alert: AlertService) { }

  ngOnInit() {
    // Lleno el selector
    this.adminService.getUsers()
      .subscribe(
        data => this.users = JSON.parse(data.toString()),
        error => this.altUsers()
      );
  }

  compare() {
    if (!this.user1 || !this.user2) {
      this.alert.raise('danger', 'Debe seleccionar a los usuarios!');
      return;
    }
    this.adminService.compareUsers(this.user1, this.user2)
      .subscribe(data => {
        this.winnerUser = JSON.parse(data.toString());
        this.alert.raise('success',
          `El Usuario con mayor tenencias es ${this.winnerUser.login.nick},
           con un total de ${Math.round(this.winnerUser.wallet.dolarFinalBalance)} dólares.`, 0);
      }, error => {
        // this.alert.raise('danger', 'Error de conexión con el servidor');
        /* Para que funcione por el momento */
        this.altWinnerUser();
        this.alert.raise('success',
          `El Usuario con mayor tenencias es ${this.winnerUser.login.nick},
          con un total de ${Math.round(this.winnerUser.wallet.dolarFinalBalance)} dólares.`, 0);
      }
      );
  }

  altWinnerUser() {
    this.winnerUser = JSON.parse(`
    {
      "roles": [
        {
          "description": "Usuario"
        },
        {
          "description": "Administrador"
        }
      ],
      "person": {
        "name": "alexis",
        "email": "tostado@gmail.com",
        "lastName": "taberna"
      },
      "wallet": {
        "dolarFinalBalance": 10592.45960753558389842510223388671875,
        "dolarAmount": 10000,
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
      },
      "login": {
        "nick": "tostado",
        "tries": 0,
        "active": true
      }
    }
    `);
  }

  altUsers() {
    this.users = JSON.parse(`
    [
      "lobezzzno",
      "tostado",
      "boberman"
    ]
    `);
  }

}
