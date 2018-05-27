import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ErrorRoutingModule } from './error-routing.module';
import { ErrorComponent } from './error.component';
import { ServerErrorComponent } from './server-error/server-error.component';
import { AccessDeniedComponent } from './access-denied/access-denied.component';
import { NotFoundComponent } from './not-found/not-found.component';

@NgModule({
  imports: [
    CommonModule,
    ErrorRoutingModule
  ],
  declarations: [
    ErrorComponent,
    ServerErrorComponent,
    AccessDeniedComponent,
    NotFoundComponent
  ]
})
export class ErrorModule { }
