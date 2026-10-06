import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule, Injectable } from '@angular/core';
import { AngularResizedEventModule } from 'angular-resize-event';
import { NgImageSliderModule } from 'ng-image-slider';
import { RouterModule } from '@angular/router';
// import { FakeBackendHttpInterceptor } from 'app/blocks/interceptor/fakebackend';
import { AuthInterceptor } from 'app/blocks/interceptor/auth.interceptor';
import { CtSharedModule } from 'app/shared/shared.module';
import { Line2Component } from './line2.component';
import { LINE1_ROUTE } from './line2.route';
import { PieComponent } from './pie/pie.component';
import { ChartBarComponent } from './chart-bar/chart-bar.component';
import { HighchartsChartModule } from 'highcharts-angular';
import { ChartNestedComponent } from './chart-nested/chart-nested.component';
import { Tab3ChartComponent } from './tab3-chart/tab3-chart.component';
import { ChartImageComponent } from './chart-image/chart-image.component';
import { Line2Service } from '../line2.service';

@NgModule({
  imports: [
    HttpClientModule,
    AngularResizedEventModule,
    NgImageSliderModule,
    CtSharedModule,
    RouterModule.forChild([LINE1_ROUTE]),
    HighchartsChartModule
  ],
  declarations: [Line2Component, PieComponent, ChartBarComponent, ChartNestedComponent, Tab3ChartComponent, ChartImageComponent],
  providers: [
    // {
    //   provide: HTTP_INTERCEPTORS,
    //   useClass: FakeBackendHttpInterceptor,
    //   multi: true
    // },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    Line2Service
  ]
})
@Injectable({
  providedIn: 'root'
})
export class CtLine2Module {}
