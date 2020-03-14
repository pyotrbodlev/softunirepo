import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BookListComponent} from './book-list/book-list.component';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {BookListItemComponent} from './book-list-item/book-list-item.component';
import {FontAwesomeModule} from '@devoto13/angular-fontawesome';
import {RouterModule} from '@angular/router';
import {BookDetailsComponent} from './book-details/book-details.component';
import {BookCreateComponent} from './book-create/book-create.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ReviewComponent} from './review/review.component';
import {ShortifyPipe} from '../shortify.pipe';
import {SharedModule} from "../shared/shared.module";
import {ToLocalDatePipe} from "../to-local-date.pipe";


@NgModule({
  declarations: [BookListComponent, BookListItemComponent, BookDetailsComponent, BookCreateComponent, ReviewComponent, ShortifyPipe, ToLocalDatePipe],
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    FontAwesomeModule,
    RouterModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    ReactiveFormsModule,
    FormsModule,
    SharedModule
  ], exports: [BookListComponent]
})
export class BookModule { }
