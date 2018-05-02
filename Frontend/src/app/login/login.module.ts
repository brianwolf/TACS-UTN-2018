import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';

@NgModule({
  imports: [CommonModule, FormsModule, NgbAlertModule.forRoot(), LoginRoutingModule],
  declarations: [LoginComponent]
})
export class LoginModule { }
