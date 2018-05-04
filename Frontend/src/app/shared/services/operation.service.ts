import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Operation } from '../model/operation';

@Injectable()
export class OperationService {
  API = 'http://localhost:8080/utn/crypto-currency/wallet/';
  constructor(private http: HttpClient) { }

  buy(operation: Operation) {
    return this.http.post(this.API + 'buy', operation);
  }

  sell(operation: Operation) {
    return this.http.post(this.API + 'sell', operation);
  }

}
