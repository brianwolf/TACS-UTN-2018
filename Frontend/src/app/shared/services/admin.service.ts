import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/map';

@Injectable()
export class AdminService {

  API = 'http://localhost:8080/utn/crypto-currency/admin/';

  constructor(private http: HttpClient) { }

  getUsers() {
    return this.http.get(this.API + 'users/nicks');
  }

  getToday() {
    return this.http.get(this.API + 'states');
  }

  getThreeDays() {
    return this.http.get(this.API + 'states/threeDays');
  }

  getLastweek() {
    return this.http.get(this.API + 'states/lastWeek');
  }

  getLastMonth() {
    return this.http.get(this.API + 'states/lastMonth');
  }

  getStartTimes() {
    return this.http.get(this.API + 'states/startTimes');
  }

}
