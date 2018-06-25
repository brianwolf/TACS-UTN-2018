import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../shared/services/user.service';

@Component({
  selector: 'app-dollar-balance',
  templateUrl: './dollar-balance.component.html'
})
export class DollarBalanceComponent implements OnInit {

  saldoUSD;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getWallet();
  }

  getWallet() {
    this.userService.getWallet().subscribe((data: any) => this.saldoUSD = data.dolarAmount);
  }

}
