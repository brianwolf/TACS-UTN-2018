import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { routerTransition } from '../../router.animations';
import { AdminService } from '../../shared/services/admin.service';

@Component({
  selector: 'app-compare',
  templateUrl: './compare.component.html',
  styles: [`
  th {
    font-weight: bold
  }
  td {
    text-align: right
  }
  `],
  animations: [routerTransition()]
})
export class CompareComponent implements OnInit {

  winner;
  loser;
  users;
  disabled = true;

  constructor(private adminService: AdminService, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.fillSelector();
  }

  fillSelector() {
    this.adminService.getUsers().subscribe(data => { this.users = data; this.disabled = false; });
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
      error => this.snackBar.open('ERROR: No se puede conectar con el servidor.', 'v', { panelClass: 'alert-danger' })
    );
  }

  // compare(user1, user2) {
  //   this.adminService.compareUsers(user1, user2)
  //     .subscribe(data => {
  //       const winnerUser: any = data;
  //       this.snackBar.open(`El Usuario con mayor tenencias es ${winnerUser.login.nick},
  //       con un total de ${Math.round(winnerUser.wallet.dolarFinalBalance)} dólares.`, 'x', { duration: 50000 });
  //     },
  //       error => this.snackBar.open('ERROR: No se puede conectar con el servidor.', 'x', { panelClass: 'alert-danger' })
  //     );
  // }

}
