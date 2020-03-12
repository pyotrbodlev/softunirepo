import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {UserModule} from './user/user.module';
import {CoreModule} from './core/core.module';
import {AppRoutingModule} from './app-routing.module';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {BookModule} from './book/book.module';
import {SharedModule} from './shared/shared.module';
import {AppInterceptor} from './app.interceptor';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    UserModule,
    CoreModule,
    AppRoutingModule,
    FontAwesomeModule,
    HttpClientModule,
    BookModule,
    SharedModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AppInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
