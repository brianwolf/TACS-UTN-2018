import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '../../app.config';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { FormControl } from '@angular/forms';

@Injectable()
export class AdminService {

  nicks: string[];
  filteredNicks: Observable<string[]>;
  nicksControl: FormControl = new FormControl();

  API;

  constructor(private http: HttpClient, private config: AppConfig) {
    this.API = config.getConfig('host');
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

  getDeposit(status: string) {
    return this.http.get(this.API + `admin/deposits?status=${status}`);
  }

  getDeposits() {
    return this.http.get(this.API + 'admin/deposits/all');
  }

  approveDeposit(number: string) {
    return this.http.put(this.API + `admin/deposits/approve/${number}`, null);
  }

  rejectDeposit(number: string) {
    return this.http.put(this.API + `admin/deposits/reject/${number}`, null);
  }

  convertAdmin(user: string) {
    return this.http.put(this.API + `admin/users/toAdmin?nick=${user}`, null);
  }


  /* NICKS */

  fillNicksSelector() {
    this.getUsers().subscribe(
      (data: any) => this.nicks = data,
      error => null,
      () => this.filteredNicks = this.nicksControl.valueChanges.pipe(startWith(''), map(val => this.filterUser(val)))
    );
  }

  filterUser(val: string): string[] {
    return this.nicks.filter(nick => nick.toLowerCase().startsWith(val.toLowerCase()));
  }

}
