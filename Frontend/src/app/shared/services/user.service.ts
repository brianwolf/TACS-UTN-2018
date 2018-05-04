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
    const requestHeader = new HttpHeaders({ 'No-Auth': 'True' });
    return this.http.post(this.API + 'users/new', user, { headers: requestHeader });
  }

  login(user: User) {
    // const body = `{"nick": "lobezzzno", "pass": "1234"}`;
    // const body = `{"nick": "${user.nick}", "pass": "${user.pass}"}`;
    const body = JSON.stringify(user);

    return this.http.post<any>(this.API + 'users/login', body)
      .map(_user => {
        if (_user && _user.token) { // si hay un JWT Token el login es exitoso
          console.log(_user.token);
          this.getUserByToken(_user.token); // guardo el usuario
          localStorage.setItem('currentUserName', user.nick);
          // guardo el token para mandarlo en todas las requests
          localStorage.setItem('currentToken', JSON.stringify(_user.token));
        }
        return _user;
      });
  }

  getUserByToken(token: string) {
    this.http
      .get(this.API + 'users', { headers: { token: token } })
      .subscribe(data => { localStorage.setItem('currentUser', JSON.stringify(data)); });
  }

  logout() {
    localStorage.removeItem('currentUser');
    localStorage.removeItem('currentUserName');
    localStorage.removeItem('currentUserRole');
  }

}
