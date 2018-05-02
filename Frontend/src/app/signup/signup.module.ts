import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgbAlertModule } from '@ng-bootstrap/ng-bootstrap';
import { SignupRoutingModule } from './signup-routing.module';
import { SignupComponent } from './signup.component';


@NgModule({
  imports: [CommonModule, FormsModule, NgbAlertModule.forRoot(), SignupRoutingModule],
  declarations: [SignupComponent]
})
export class SignupModule { }
