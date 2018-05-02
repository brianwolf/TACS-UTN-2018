import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { WalletRoutingModule } from './wallet-routing.module';
import { WalletComponent } from './wallet.component';
import { PageHeaderModule } from '../../shared/modules/page-header/page-header.module';

@NgModule({
  imports: [
    CommonModule,
    PageHeaderModule,
    NgbAlertModule.forRoot(),
    WalletRoutingModule
  ],
  declarations: [WalletComponent]
})
export class WalletModule { }
