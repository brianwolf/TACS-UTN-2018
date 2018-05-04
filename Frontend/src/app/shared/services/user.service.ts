import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { User } from '../model/user';

@Injectable()
export class UserService {
  API = 'http://localhost:8080/utn/crypto-currency/';
  constructor(private http: HttpClient) { }

  isAdmin() {
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    return (currentUser && currentUser.admin) ? true : false;
  }

  signup(user: User) {
    const body = `{"nick": "${user.nick}", "pass": "${user.pass}"}`;
    return this.http.post(this.API + 'users/new', body);
  }

  login(user: User) {
    // const body = `{"nick": "lobezzzno", "pass": "1234"}`;
    const body = `{"nick": "${user.nick}", "pass": "${user.pass}"}`;

    return this.http.post<any>(this.API + 'users/login', body)
      .map(_user => {
        if (_user && _user.token) { // si hay un JWT Token el login es exitoso
          console.log(_user.token);
          // this.getUserByToken(_user.token); // guardo el usuario
          localStorage.setItem('currentUserName', user.nick);
          // guardo el token para mandarlo en todas las requests
          localStorage.setItem('currentToken', JSON.stringify(_user.token));
        }
        return _user;
      });
  }

  getUserByToken() {
    return this.http.get(this.API + 'users/loggedin');
  }

  getWallet() {
    return this.http.get(this.API + 'wallet');
  }

  getTransactions() {
    return this.http.get(this.API + 'wallet/history/transactions');
  }

  logout() {
    // localStorage.removeItem('currentUser');
    localStorage.removeItem('currentUserName');
    localStorage.removeItem('currentUserRole');
    // this.http.put(this.API + 'users/logout');
  }

}
