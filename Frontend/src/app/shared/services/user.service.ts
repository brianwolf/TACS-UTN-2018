import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '../../app.config';

@Injectable()
export class UserService {

  API;

  constructor(private http: HttpClient, private config: AppConfig) {
    this.API = config.getConfig('API');
  }

  signup(user) {
    return this.http.post(this.API + 'users', user);
  }

  modify(body) {
    return this.http.put(this.API + 'users', body);
  }

  login(user) {
    return this.http.post(this.API + 'users/login', user);
  }

  relog(user) {
    return this.http.post(this.API + `users/relog/${user}`, null);
  }

  logout() {
    return this.http.put(this.API + 'users/logout', null);
  }

  getUser() {
    return this.http.get(this.API + 'users/loggedin');
  }

  getWallet() {
    return this.http.get(this.API + 'wallet');
  }

  getWalletByCoin(coin) {
    return this.http.get(this.API + `wallet?ticker=${coin}`);
  }

  buy(operation) {
    return this.http.post(this.API + 'wallet/buy', operation);
  }

  sell(operation) {
    return this.http.post(this.API + 'wallet/sale', operation);
  }

  deposit(operation) {
    return this.http.post(this.API + 'wallet/deposits', operation);
  }

  deposits() {
    return this.http.get(this.API + 'wallet/deposits');
  }

  getTransactions() {
    return this.http.get(this.API + 'wallet/history/transactions');
  }

  getAllCoins() {
    return this.http.get(this.API + 'services/external/coins');
  }

}
