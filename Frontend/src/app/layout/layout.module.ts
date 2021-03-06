import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  MatAutocompleteModule, MatButtonModule, MatIconModule,
  MatInputModule, MatPaginatorModule, MatSelectModule,
  MatSortModule, MatTableModule, MatToolbarModule
} from '@angular/material';
import { TranslateModule } from '@ngx-translate/core';
import { NgbDropdownModule, NgbCarouselModule } from '@ng-bootstrap/ng-bootstrap';
import { LayoutRoutingModule } from './layout-routing.module';
import { LayoutComponent } from './layout.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { TimelineComponent } from './dashboard/components/timeline/timeline.component';
import { PageHeaderComponent } from './components/page-header/page-header.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { HeaderComponent } from './components/header/header.component';
import { BuyComponent } from './buy/buy.component';
import { SellComponent } from './sell/sell.component';
import { DepositComponent } from './deposit/deposit.component';
import { WalletComponent } from './wallet/wallet.component';
import { UserComponent } from './user/user.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { DepositsComponent } from './deposits/deposits.component';
import { AuthorizeComponent } from './authorize/authorize.component';
import { UsersComponent } from './users/users.component';
import { CompareComponent } from './compare/compare.component';
import { HistoryComponent } from './history/history.component';
import { DollarBalanceComponent } from './components/dollar-balance/dollar-balance.component';
import { NickSelectorComponent } from './components/nick-selector/nick-selector.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    LayoutRoutingModule,
    MatAutocompleteModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatPaginatorModule,
    MatSelectModule,
    MatSortModule,
    MatTableModule,
    MatToolbarModule,
    NgbCarouselModule.forRoot(),
    NgbDropdownModule.forRoot(),
    ReactiveFormsModule,
    TranslateModule
  ],
  declarations: [
    LayoutComponent,
    DashboardComponent,
    TimelineComponent,
    PageHeaderComponent,
    SidebarComponent,
    HeaderComponent,
    BuyComponent,
    SellComponent,
    DepositComponent,
    WalletComponent,
    UserComponent,
    TransactionsComponent,
    DepositsComponent,
    AuthorizeComponent,
    UsersComponent,
    CompareComponent,
    HistoryComponent,
    DollarBalanceComponent,
    NickSelectorComponent
  ]
})
export class LayoutModule { }
