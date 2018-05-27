import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import {
  MatButtonModule, MatIconModule, MatInputModule,
  MatPaginatorModule, MatSelectModule,
  MatSortModule, MatTableModule, MatToolbarModule
} from '@angular/material';
import { TranslateModule } from '@ngx-translate/core';
import { NgbDropdownModule } from '@ng-bootstrap/ng-bootstrap';
import { LayoutRoutingModule } from './layout-routing.module';
import { LayoutComponent } from './layout.component';
import { PageHeaderComponent } from './components/page-header/page-header.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { HeaderComponent } from './components/header/header.component';
import { BuyComponent } from './buy/buy.component';
import { SellComponent } from './sell/sell.component';
import { DepositComponent } from './deposit/deposit.component';
import { WalletComponent } from './wallet/wallet.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { AuthorizeComponent } from './authorize/authorize.component';
import { UsersComponent } from './users/users.component';
import { CompareComponent } from './compare/compare.component';
import { HistoryComponent } from './history/history.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    LayoutRoutingModule,
    TranslateModule,
    NgbDropdownModule.forRoot(),
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatPaginatorModule,
    MatSelectModule,
    MatSortModule,
    MatTableModule,
    MatToolbarModule
  ],
  declarations: [
    LayoutComponent,
    PageHeaderComponent,
    SidebarComponent,
    HeaderComponent,
    BuyComponent,
    SellComponent,
    DepositComponent,
    WalletComponent,
    TransactionsComponent,
    AuthorizeComponent,
    UsersComponent,
    CompareComponent,
    HistoryComponent
  ]
})
export class LayoutModule { }
