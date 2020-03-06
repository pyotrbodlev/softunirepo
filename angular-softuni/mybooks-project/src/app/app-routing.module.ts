import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {RegisterComponent} from './user/register/register.component';
import {LoginComponent} from './user/login/login.component';
import {WelcomeComponent} from './core/welcome/welcome.component';
import {BookListComponent} from './book/book-list/book-list.component';
import {BookListItemComponent} from './book/book-list-item/book-list-item.component';
import {BookDetailsComponent} from './book/book-details/book-details.component';

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
  },
  {
    path: 'books',
    component: BookListComponent,
  },
  {
    path: 'books/:id',
    component: BookDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
