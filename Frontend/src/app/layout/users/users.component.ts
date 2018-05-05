import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { User } from '../../shared/model/user';
import { AdminService } from '../../shared/services/admin.service';
import { AlertService } from '../../shared/services/alert.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  animations: [routerTransition()]
})
export class UsersComponent implements OnInit {

  users: any[];
  user: any;
  userSelected: string;
  showInfo: boolean;

  constructor(private adminService: AdminService, private alert: AlertService) { }

  ngOnInit() {
    // Lleno el selector
    this.adminService.getUsers()
      .subscribe(
        data => this.users = JSON.parse(data.toString()),
        error => this.altUsers()
      );
  }

  getUser() {
    if (!this.userSelected) {
      this.alert.raise('danger', 'Debe seleccionar al usuario!');
      return;
    }
    this.adminService.getUser(this.userSelected)
      .subscribe(data => {
        this.user = JSON.parse(data.toString());
        this.showInfo = true;
      }, error => {
        this.alert.raise('danger', 'Error de conexión con el servidor');
        /* Para que funcione por el momento */
        this.altUser();
        this.showInfo = true;
      }
      );
  }

  altUser() {
    this.user = JSON.parse(`
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
