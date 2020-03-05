import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AppComponent} from './app.component';
import {RegisterComponent} from './user/register/register.component';
import {LoginComponent} from './user/login/login.component';
import {WelcomeComponent} from './core/welcome/welcome.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: WelcomeComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'login',
    component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
