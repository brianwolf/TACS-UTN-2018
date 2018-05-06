import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { routerTransition } from '../router.animations';
import { AlertService } from '../shared/services/alert.service';
import { UserService } from '../shared/services/user.service';
import { User } from '../shared/model/user';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  animations: [routerTransition()]
})
export class LoginComponent implements OnInit {

  user: User;
  data: any;

  constructor(public alert: AlertService, public router: Router, private userService: UserService) { }

  ngOnInit() {
    this.user = new User();
  }

  onSubmit(form: NgForm) {
    this.userService.login(this.user)
      .map(_user => {
        if (_user && _user.token) { // si hay un JWT Token el login es exitoso
          console.log(_user.token);
          // guardo el token para mandarlo en todas las requests
          localStorage.setItem('currentToken', JSON.stringify(_user.token));
        }
        return _user;
      })
      .subscribe(
        data => {
          this.checkAdmin();
          localStorage.setItem('currentUserName', this.user.nick);
          this.router.navigate(['dashboard']);
        },
        error => {
          this.alert.raise('danger', 'Error de conexión con el servidor');
          //  Esto es para que funcione mientras se integra con la API
          this.checkAdmin();
          localStorage.setItem('currentUserName', this.user.nick);
        });
  }

  checkAdmin() {
    let info: any;
    this.userService.getUserByToken()
      .subscribe(
        data => {
          info = JSON.parse(data.toString());
          if (info.roles[1].description === 'Administrador') {
            localStorage.setItem('currentUserRole', 'Admin');
          } else if (info.roles[1].description === 'Usuario') {
            localStorage.setItem('currentUserRole', 'User');
          }
        },
        error => {
          //  Esto es para que funcione mientras se integra con la API
          info = this.dataAlternativa();
          console.log(info);
          if (info.roles[1]) {
            localStorage.setItem('currentUserRole', 'Admin');
          } else if (info.roles[0]) {
            localStorage.setItem('currentUserRole', 'User');
          }
          this.router.navigate(['dashboard']);
        }
      );
  }

  dataAlternativa(): any {
    return JSON.parse(`
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
        "name": "brian",
        "email": "lobezzzno@gmail.com",
        "lastName": "lobo"
      },
      "wallet": {
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
      },
      "login": {
        "nick": "lobezzzno",
        "tries": 0,
        "active": true
      }
    }
    `);
  }

}
