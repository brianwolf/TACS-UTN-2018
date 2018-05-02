import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';

@Component({
  selector: 'app-wallet',
  templateUrl: './wallet.component.html',
  styleUrls: ['./wallet.component.scss'],
  animations: [routerTransition()]
})
export class WalletComponent implements OnInit {

  constructor() { }

  ngOnInit() { }

}
