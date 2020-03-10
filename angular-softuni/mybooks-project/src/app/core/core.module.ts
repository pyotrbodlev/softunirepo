import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {NavbarComponent} from './navbar/navbar.component';
import {RouterModule} from '@angular/router';
import {MatButtonModule} from '@angular/material/button';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {WelcomeComponent} from './welcome/welcome.component';
import {FontAwesomeModule} from '@devoto13/angular-fontawesome';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {FooterComponent} from './footer/footer.component';

@NgModule({
  declarations: [NavbarComponent, WelcomeComponent, FooterComponent],
  exports: [
    NavbarComponent, FooterComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    MatButtonModule,
    MatMenuModule,
    MatIconModule,
    FontAwesomeModule,
    MatProgressBarModule
  ]
})
export class CoreModule {
}
