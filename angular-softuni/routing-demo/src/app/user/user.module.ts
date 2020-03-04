import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ListComponent} from './list/list.component';
import {DetailsComponent} from './details/details.component';
import {RouterModule} from '@angular/router';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {SharedModule} from '../shared/shared.module';


@NgModule({
  declarations: [ListComponent, DetailsComponent],
  exports: [
    ListComponent, DetailsComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    MatProgressSpinnerModule,
    SharedModule
  ]
})
export class UserModule {
}
