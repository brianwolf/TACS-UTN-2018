import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TranslateModule } from '@ngx-translate/core';
import { NgbDropdownModule, NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { LayoutRoutingModule } from './layout-routing.module';
import { LayoutComponent } from './layout.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { HeaderComponent } from './components/header/header.component';
import { PageHeaderModule } from '../shared/modules/page-header/page-header.module';
import { BuyComponent } from './buy/buy.component';
import { SellComponent } from './sell/sell.component';
import { WalletComponent } from './wallet/wallet.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { UsersComponent } from './users/users.component';
import { HistoryComponent } from './history/history.component';
import { CompareComponent } from './compare/compare.component';
import { AlertService } from '../shared/services/alert.service';
import { UserService } from '../shared/services/user.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor } from '../shared/services/jwt.interceptor';
import { AdminService } from '../shared/services/admin.service';
import { OperationService } from '../shared/services/operation.service';
import { FormsModule } from '@angular/forms';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    PageHeaderModule,
    NgbAlertModule.forRoot(),
    LayoutRoutingModule,
    TranslateModule,
    NgbDropdownModule.forRoot()
  ],
  declarations: [
    LayoutComponent,
    SidebarComponent,
    HeaderComponent,
    BuyComponent,
    SellComponent,
    WalletComponent,
    TransactionsComponent,
    CompareComponent,
    UsersComponent,
    CompareComponent,
    HistoryComponent
  ],
  providers: [
    AlertService,
    AdminService,
    UserService,
    OperationService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ]
})
export class LayoutModule { }
