import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { BuyComponent } from './buy.component';
import { BuyRoutingModule } from './buy-routing.module';
import { PageHeaderModule } from '../../shared/modules/page-header/page-header.module';

@NgModule({
  imports: [
    CommonModule,
    PageHeaderModule,
    NgbAlertModule.forRoot(),
    BuyRoutingModule
  ],
  declarations: [BuyComponent]
})
export class BuyModule { }
