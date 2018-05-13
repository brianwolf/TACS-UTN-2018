import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { UserService } from '../../shared/services/user.service';

@Component({
  selector: 'app-wallet',
  templateUrl: './wallet.component.html',
  animations: [routerTransition()]
})
export class WalletComponent implements OnInit {

  wallet;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getWallet();
  }

  getWallet() {
    this.userService.getWallet().subscribe(data => this.wallet = data);
  }

}
