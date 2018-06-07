import { Component, OnInit } from '@angular/core';
import { FormControl, NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { routerTransition } from '../../router.animations';
import { AdminService } from '../../shared/services/admin.service';

@Component({
  selector: 'app-compare',
  templateUrl: './compare.component.html',
  styles: [`
  th { font-weight: bold }
  td { text-align: right }
  mat-form-field { width: 100% }
  `],
  animations: [routerTransition()]
})
export class CompareComponent implements OnInit {

  disabled;
  winner;
  loser;

  constructor(public adminService: AdminService, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.adminService.fillNicksSelector();
  }

  activateButton() {
    this.winner = null;
    this.disabled = false;
  }

  compare(nick1, nick2) {
    this.disabled = true;
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
          }
        )
      ,
      error => this.snackBar.open(error.error.message, 'x', { panelClass: 'alert-danger' }),
    );
  }

}
