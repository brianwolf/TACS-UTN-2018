import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SignRoutingModule } from './sign-routing.module';
import { SignComponent } from './sign.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { RelogComponent } from './relog/relog.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    SignRoutingModule
  ],
  declarations: [
    SignComponent,
    LoginComponent,
    SignupComponent,
    RelogComponent
  ]
})
export class SignModule { }
