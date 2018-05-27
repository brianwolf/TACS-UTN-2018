import { NgModule, APP_INITIALIZER } from '@angular/core';
import { OVERLAY_PROVIDERS } from '@angular/cdk/overlay';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatSnackBarModule, MAT_SNACK_BAR_DEFAULT_OPTIONS } from '@angular/material';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule, HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { AppConfig } from './app.config';
import { AppRoutingModule } from './app-routing.module';
import { AdminService } from './shared/services/admin.service';
import { UserService } from './shared/services/user.service';
import { JwtInterceptor } from './shared/services/jwt.interceptor';
import { AuthGuard } from './shared/guard/auth.guard';
import { RoleGuard } from './shared/guard/role.guard';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { ServerErrorComponent } from './error/server-error/server-error.component';
import { AccessDeniedComponent } from './error/access-denied/access-denied.component';
import { NotFoundComponent } from './error/not-found/not-found.component';
import { ErrorFooterComponent } from './error/error-footer/error-footer.component';

// AoT requires an exported function for factories
export function createTranslateLoader(http: HttpClient) {
  // for development
  // return new TranslateHttpLoader(http, '/start-angular/TACS-Crypto/master/dist/assets/i18n/', '.json');
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
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    ServerErrorComponent,
    AccessDeniedComponent,
    NotFoundComponent,
    ErrorFooterComponent
  ],
  providers: [
    AdminService,
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
      provide: MAT_SNACK_BAR_DEFAULT_OPTIONS,
      useValue: { duration: 3000, panelClass: 'alert-success' }
    },
    AppConfig,
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

