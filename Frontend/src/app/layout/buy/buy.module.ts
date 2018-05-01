import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BuyComponent } from './buy.component';
import { BuyRoutingModule } from './buy-routing.module';
import { PageHeaderModule } from '../../shared/index';

@NgModule({
  imports: [CommonModule, BuyRoutingModule, PageHeaderModule],
  declarations: [BuyComponent]
})
export class BuyModule { }
