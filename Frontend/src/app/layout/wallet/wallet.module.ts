import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WalletRoutingModule } from './wallet-routing.module';
import { WalletComponent } from './wallet.component';
import { PageHeaderModule } from '../../shared/index';

@NgModule({
  imports: [CommonModule, WalletRoutingModule, PageHeaderModule],
  declarations: [WalletComponent]
})
export class WalletModule { }
