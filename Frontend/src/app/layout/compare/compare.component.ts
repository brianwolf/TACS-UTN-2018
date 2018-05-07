import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { routerTransition } from '../../router.animations';
import { AdminService } from '../../shared/services/admin.service';
import { AlertService } from '../../shared/services/alert.service';

@Component({
  selector: 'app-compare',
  templateUrl: './compare.component.html',
  animations: [routerTransition()]
})
export class CompareComponent implements OnInit {

  users: any[];
  winnerUser: any;
  user1: string;
  user2: string;

  constructor(private adminService: AdminService, private alert: AlertService) { }

  ngOnInit() {
    this.fillSelector();
  }

  fillSelector() {
    this.adminService.getUsers().subscribe(data => this.users = (<any>data));
  }

  compare() {
    this.adminService.compareUsers(this.user1, this.user2)
      .subscribe(data => {
        this.winnerUser = data;
        this.alert.raise('success',
          `El Usuario con mayor tenencias es ${this.winnerUser.login.nick},
           con un total de ${Math.round(this.winnerUser.wallet.dolarFinalBalance)} dólares.`, 10000);
      },
      error => this.alert.raise('danger', 'ERROR: No se puede conectar con el servidor.')
    );
  }

}
