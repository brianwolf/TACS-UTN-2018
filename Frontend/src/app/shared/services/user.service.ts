import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class UserService {

  API = 'http://localhost:8080/utn/crypto-currency/';

  constructor(private http: HttpClient) { }

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

  buy(operation) {
    return this.http.post(this.API + 'wallet/buy', operation);
  }

  sell(operation) {
    return this.http.post(this.API + 'wallet/sale', operation);
  }

  getTransactions() {
    return this.http.get(this.API + 'wallet/history/transactions');
  }

}
