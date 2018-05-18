import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import {
  MatButtonModule, MatInputModule, MatToolbarModule,
  MatTableModule, MatPaginatorModule, MatSortModule
} from '@angular/material';
import { TranslateModule } from '@ngx-translate/core';
import { NgbDropdownModule, NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { LayoutRoutingModule } from './layout-routing.module';
import { LayoutComponent } from './layout.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { HeaderComponent } from './components/header/header.component';
import { PageHeaderModule } from '../shared/modules/page-header/page-header.module';
import { BuyComponent } from './buy/buy.component';
import { SellComponent } from './sell/sell.component';
import { DepositComponent } from './deposit/deposit.component';
import { WalletComponent } from './wallet/wallet.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { AuthorizeComponent } from './authorize/authorize.component';
import { UsersComponent } from './users/users.component';
import { CompareComponent } from './compare/compare.component';
import { HistoryComponent } from './history/history.component';
import { AlertService } from '../shared/services/alert.service';
import { UserService } from '../shared/services/user.service';
import { JwtInterceptor } from '../shared/services/jwt.interceptor';
import { AdminService } from '../shared/services/admin.service';
import { OVERLAY_PROVIDERS } from '@angular/cdk/overlay';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    PageHeaderModule,
    NgbAlertModule.forRoot(),
    LayoutRoutingModule,
    TranslateModule,
    NgbDropdownModule.forRoot(),
    MatButtonModule,
    MatInputModule,
    MatToolbarModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
  ],
  declarations: [
    LayoutComponent,
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
  ],
  providers: [
    AlertService,
    AdminService,
    UserService,
    OVERLAY_PROVIDERS,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ]
})
export class LayoutModule { }
