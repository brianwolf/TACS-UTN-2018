import { NgModule, APP_INITIALIZER } from '@angular/core';
import { OVERLAY_PROVIDERS } from '@angular/cdk/overlay';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatSnackBarModule } from '@angular/material';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { AdminService } from './shared/services/admin.service';
import { AlertService } from './shared/services/alert.service';
import { AppComponent } from './app.component';
import { AppConfig } from './app.config';
import { AppRoutingModule } from './app-routing.module';
import { AuthGuard } from './shared/guard/auth.guard';
import { ComponentService } from './shared/services/component.service';
import { JwtInterceptor } from './shared/services/jwt.interceptor';
import { RoleGuard } from './shared/guard/role.guard';
import { UserService } from './shared/services/user.service';

// AoT requires an exported function for factories
export function createTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatSnackBarModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: createTranslateLoader,
        deps: [HttpClient]
      }
    }),
    AppRoutingModule
  ],
  declarations: [AppComponent],
  providers: [
    AdminService,
    AlertService,
    AppConfig,
    ComponentService,
    UserService,
    AuthGuard,
    RoleGuard,
    OVERLAY_PROVIDERS,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    },
    {
      provide: APP_INITIALIZER,
      useFactory: (config: AppConfig) => () => config.load(),
      deps: [AppConfig],
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
