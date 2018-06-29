import { Component } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { AlertService } from '../../shared/services/alert.service';
import { AdminService } from '../../shared/services/admin.service';

@Component({
  selector: 'app-compare',
  templateUrl: './compare.component.html',
  styles: [`
  th { font-weight: bold }
  td { text-align: right }
  `],
  animations: [routerTransition()]
})
export class CompareComponent {

  winner;
  loser;

  proccesing;

  constructor(public alertService: AlertService, public adminService: AdminService) { }

  compare(nick1, nick2) {
    this.proccesing = true;
    this.adminService.getUser(nick1).subscribe(
      (user1: any) =>
        this.adminService.getUser(nick2).subscribe(
          (user2: any) => {
            if (user2.user.wallet.dolarFinalBalance > user1.user.wallet.dolarFinalBalance) {
              this.winner = user2.user;
              this.loser = user1.user;
            } else {
              this.winner = user1.user;
              this.loser = user2.user;
            }
            this.proccesing = false;
          }
        )
      ,
      error => this.alertService.error(error.error.message)
    );
  }

}
