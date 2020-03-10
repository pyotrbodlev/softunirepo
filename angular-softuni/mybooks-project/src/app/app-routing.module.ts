import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {RegisterComponent} from './user/register/register.component';
import {LoginComponent} from './user/login/login.component';
import {WelcomeComponent} from './core/welcome/welcome.component';
import {BookListComponent} from './book/book-list/book-list.component';
import {BookDetailsComponent} from './book/book-details/book-details.component';
import {BookCreateComponent} from './book/book-create/book-create.component';
import {UserProfileComponent} from './user/user-profile/user-profile.component';

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
    path: 'books/create',
    pathMatch: 'full',
    component: BookCreateComponent
  },
  {
    path: 'books/:id',
    component: BookDetailsComponent
  },
  {
    path: 'user/:id',
    component: UserProfileComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
