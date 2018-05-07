import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class AdminService {

  API = 'http://localhost:8080/utn/crypto-currency/admin/';

  constructor(private http: HttpClient) { }

  getUser(user: string) {
    return this.http.get(this.API + `users/${user}`);
  }

  getUsers() {
    return this.http.get(this.API + 'users/nicks');
  }

  compareUsers(user1: string, user2: string) {
    return this.http.get(this.API + `balance/${user1}/${user2}`);
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
