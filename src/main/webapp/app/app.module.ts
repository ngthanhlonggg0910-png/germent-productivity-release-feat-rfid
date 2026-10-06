import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AngularResizedEventModule } from 'angular-resize-event';

import './vendor';
import { CtSharedModule } from 'app/shared/shared.module';
import { CtCoreModule } from 'app/core/core.module';
import { CtAppRoutingModule } from './app-routing.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { JhiMainComponent } from './layouts/main/main.component';
import { HttpClientModule } from '@angular/common/http';
import { CtLayoutModule } from './layouts/layout.module';

@NgModule({
  imports: [
    HttpClientModule,
    BrowserModule,
    AngularResizedEventModule,
    CtSharedModule,
    CtCoreModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    CtAppRoutingModule,
    CtLayoutModule
  ],
  declarations: [JhiMainComponent],
  bootstrap: [JhiMainComponent]
})
export class CtAppModule {}
