import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { routerTransition } from '../../router.animations';
import { AdminService } from '../../shared/services/admin.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styles: [' td { text-align:center } '],
  animations: [routerTransition()]
})
export class UsersComponent implements OnInit {

  user;
  users;
  showInfo: boolean;

  constructor(private adminService: AdminService, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.fillSelector();
  }

  fillSelector() {
    this.adminService.getUsers().subscribe(data => this.users = data);
  }

  getUser(userSelected) {
    this.adminService.getUser(userSelected)
      .subscribe(
        data => {
          this.user = data;
          this.showInfo = true;
        },
        error => this.snackBar.open('ERROR: No se puede conectar con el servidor.', 'x', { panelClass: 'alert-danger' })
      );
  }

}
