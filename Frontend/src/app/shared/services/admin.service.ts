import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class AdminService {

  API = 'http://localhost:8080/utn/crypto-currency/';

  constructor(private http: HttpClient) {
    this.http.get('./assets/config.json').subscribe((data: any) => this.API = data.API);
  }

  getUser(user: string) {
    return this.http.get(this.API + `admin/users?nick=${user}`);
  }

  getUsers() {
    return this.http.get(this.API + 'admin/users/nicks');
  }

  compareUsers(user1: string, user2: string) {
    return this.http.get(this.API + `admin/balance/${user1}/${user2}`);
  }

  getToday() {
    return this.http.get(this.API + 'admin/states?beforeDays=0');
  }

  getThreeDays() {
    return this.http.get(this.API + 'admin/states/?beforeDays=3');
  }

  getLastweek() {
    return this.http.get(this.API + 'admin/states/lastweek');
  }

  getLastMonth() {
    return this.http.get(this.API + 'admin/states/lastmonth');
  }

  getStartTimes() {
    return this.http.get(this.API + 'admin/states/all');
  }

}
