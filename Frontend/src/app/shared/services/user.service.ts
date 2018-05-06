import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';
import { Operation } from '../model/operation';

@Injectable()
export class UserService {

  API = 'http://localhost:8080/utn/crypto-currency/';

  constructor(private http: HttpClient) { }

  signup(user: User) {
    const body = { 'login': { 'nick': user.nick, 'pass': user.pass } };
    return this.http.post(this.API + 'users', JSON.stringify(body));
  }

  login(user: User) {
    // const body = `{"nick": "lobezzzno", "pass": "1234"}`;
    // const body = `{"nick": "${user.nick}", "pass": "${user.pass}"}`;
    const body = { nick: user.nick, pass: user.pass };
    return this.http.post<any>(this.API + 'users/login', JSON.stringify(body));
  }

  logout() {
    return this.http.put(this.API + 'users/logout', null);
  }

  getUserByToken() {
    return this.http.get(this.API + 'users/loggedin');
  }

  getWallet() {
    return this.http.get(this.API + 'wallet');
  }

  buy(operation: Operation) {
    return this.http.post(this.API + 'wallet/buy', operation);
  }

  sell(operation: Operation) {
    return this.http.post(this.API + 'wallet/sell', operation);
  }

  getTransactions() {
    return this.http.get(this.API + 'wallet/history/transactions');
  }

}
