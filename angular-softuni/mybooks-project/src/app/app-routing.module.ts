import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from '../user/register/register.component';
import {LoginComponent} from '../user/login/login.component';
import {WelcomeComponent} from '../core/welcome/welcome.component';
import {BookListComponent} from '../book/book-list/book-list.component';
import {BookDetailsComponent} from '../book/book-details/book-details.component';
import {BookCreateComponent} from '../book/book-create/book-create.component';
import {UserProfileComponent} from '../user/user-profile/user-profile.component';
import {AuthenticateGuard} from './authenticate.guard';
import {UserProfileEditComponent} from '../user/user-profile-edit/user-profile-edit.component';

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
    canActivate: [AuthenticateGuard]
  },
  {
    path: 'books/create',
    pathMatch: 'full',
    component: BookCreateComponent,
    canActivate: [AuthenticateGuard]
  },
  {
    path: 'books/:id',
    component: BookDetailsComponent,
    canActivate: [AuthenticateGuard]
  },
  {
    path: 'user/:id',
    component: UserProfileComponent,
    canActivate: [AuthenticateGuard]
  },
  {
    path: 'user/:id/edit',
    component: UserProfileEditComponent,
    canActivate: [AuthenticateGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
