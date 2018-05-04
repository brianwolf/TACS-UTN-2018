import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { PageHeaderModule } from '../../shared/modules/page-header/page-header.module';
import { TransactionsRoutingModule } from './transactions-routing.module';
import { TransactionsComponent } from './transactions.component';

@NgModule({
  imports: [
    CommonModule,
    PageHeaderModule,
    NgbAlertModule.forRoot(),
    TransactionsRoutingModule
  ],
  declarations: [TransactionsComponent]
})
export class TransactionsModule { }
