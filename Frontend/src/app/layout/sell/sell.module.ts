import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { SellRoutingModule } from './sell-routing.module';
import { SellComponent } from './sell.component';
import { PageHeaderModule } from '../../shared/modules/page-header/page-header.module';

@NgModule({
  imports: [
    CommonModule,
    PageHeaderModule,
    NgbAlertModule.forRoot(),
    SellRoutingModule
  ],
  declarations: [SellComponent]
})
export class SellModule { }
