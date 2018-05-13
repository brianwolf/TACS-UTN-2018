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

  users;

  constructor(private adminService: AdminService, private alert: AlertService) { }

  ngOnInit() {
    this.fillSelector();
  }

  fillSelector() {
    this.adminService.getUsers().subscribe(data => this.users = data);
  }

  compare(user1, user2) {
    this.adminService.compareUsers(user1, user2)
      .subscribe(data => {
        const winnerUser: any = data;
        this.alert.raise('success',
          `El Usuario con mayor tenencias es ${winnerUser.login.nick},
           con un total de ${Math.round(winnerUser.wallet.dolarFinalBalance)} dólares.`, 10000);
      },
        error => this.alert.raise('danger', 'ERROR: No se puede conectar con el servidor.')
      );
  }

}
