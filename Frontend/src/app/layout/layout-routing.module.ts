import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RoleGuard } from '../shared/guard/role.guard';
import { LayoutComponent } from './layout.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { BuyComponent } from './buy/buy.component';
import { SellComponent } from './sell/sell.component';
import { DepositComponent } from './deposit/deposit.component';
import { WalletComponent } from './wallet/wallet.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { AuthorizeComponent } from './authorize/authorize.component';
import { UsersComponent } from './users/users.component';
import { CompareComponent } from './compare/compare.component';
import { HistoryComponent } from './history/history.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', redirectTo: 'dashboard' },
      { path: 'dashboard', component: DashboardComponent },
      { path: 'buy', component: BuyComponent },
      { path: 'sell', component: SellComponent },
      { path: 'deposit', component: DepositComponent },
      { path: 'wallet', component: WalletComponent },
      { path: 'transactions', component: TransactionsComponent },
      { path: 'authorize', component: AuthorizeComponent, canActivate: [RoleGuard] },
      { path: 'users', component: UsersComponent, canActivate: [RoleGuard] },
      { path: 'compare', component: CompareComponent, canActivate: [RoleGuard] },
      { path: 'history', component: HistoryComponent, canActivate: [RoleGuard] }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LayoutRoutingModule { }
