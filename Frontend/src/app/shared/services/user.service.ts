import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Response } from '@angular/http';
import 'rxjs/add/operator/map';
import { User } from '../model/user';

@Injectable()
export class UserService {
  API = 'http://localhost:8080/apiweb/';
  constructor(private http: HttpClient) { }

  signup(user: User) {
    const requestHeader = new HttpHeaders({ 'No-Auth': 'True' });
    return this.http.post(this.API + 'users/new', user, { headers: requestHeader });
  }


  // login(username: string, password: string) {
  //   return this.http.post<any>('login', { username: username, password: password })
  login(user: User) {
    return this.http.post<any>(this.API + 'login', user)
      .map(_user => {
        // si hay un JWT Token el login es exitoso
        if (_user && _user.token) {
          // guardo el token para mandarlo en todas las requests
          localStorage.setItem('currentUser', JSON.stringify(_user));
        }
        return _user;
      });
  }

  logout() {
    localStorage.removeItem('currentUser');
  }

  // userAuthentication(user: User) {
  //   // const requestBody = 'username=' + user.userName + '&password=' + user.password + '&grant_type=password';
  //   const requestHeader = new HttpHeaders({ 'Content-Type': 'application/x-www-urlencoded', 'No-Auth': 'True' });
  //   return this.http.post(this.API + 'login', requestBody, { headers: requestHeader });
  // }

}
