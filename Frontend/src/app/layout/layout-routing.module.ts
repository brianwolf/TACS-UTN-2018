import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './layout.component';
import { RoleGuard } from '../shared/guard/role.guard';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', redirectTo: 'dashboard' },
      { path: 'dashboard', loadChildren: './dashboard/dashboard.module#DashboardModule' },
      { path: 'buy', loadChildren: './buy/buy.module#BuyModule' },
      { path: 'sell', loadChildren: './sell/sell.module#SellModule' },
      { path: 'wallet', loadChildren: './wallet/wallet.module#WalletModule' },
      { path: 'transactions', loadChildren: './transactions/transactions.module#TransactionsModule' },
      { path: 'users', loadChildren: './transactions/transactions.module#TransactionsModule', canActivate: [RoleGuard] },
      { path: 'compare', loadChildren: './transactions/transactions.module#TransactionsModule', canActivate: [RoleGuard] },
      { path: 'history', loadChildren: './transactions/transactions.module#TransactionsModule', canActivate: [RoleGuard] }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LayoutRoutingModule { }
