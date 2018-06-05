import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '../../app.config';

@Injectable()
export class UserService {

  API;

  constructor(private http: HttpClient, private config: AppConfig) {
    this.API = config.getConfig('host');
  }

  signup(user) {
    return this.http.post(this.API + 'users', user);
  }

  login(user) {
    return this.http.post<any>(this.API + 'users/login', user);
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
    return this.http.post(this.API + 'wallet/deposit', operation);
  }

  getTransactions() {
    return this.http.get(this.API + 'wallet/history/transactions');
  }

  getAllCoins() {
    return this.http.get(this.API + 'services/external/coins');
  }

  relog(user) {
    return this.http.post(this.API + 'users/relog/'+user.nick,null);
  }

}
