import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './components/app/app.component';
import { NavComponent } from './components/nav/nav.component';
import { MainComponent } from './components/main/main.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CausesComponent } from './components/causes/causes.component';
import { CauseFullInfoComponent } from './components/cause-full-info/cause-full-info.component';
import { CauseComponent } from './components/cause/cause.component';
import {HttpClientModule} from '@angular/common/http';
import { CreateNewCauseComponent } from './components/create-new-cause/create-new-cause.component';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    MainComponent,
    CausesComponent,
    CauseFullInfoComponent,
    CauseComponent,
    CreateNewCauseComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
