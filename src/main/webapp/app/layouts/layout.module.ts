import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { HttpClientModule } from '@angular/common/http';
import { LayoutComponent } from './layout.component';
import { HomeComponent } from 'app/home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ErrorComponent } from './error/error.component';
import { PageRibbonComponent } from './profiles/page-ribbon.component';
import { FooterComponent } from './footer/footer.component';
import { ActiveMenuDirective } from './navbar/active-menu.directive';
import { CtHomeModule } from 'app/home/home.module';
import { CtEntityModule } from 'app/entities/entity.module';

@NgModule({
  imports: [
    CtHomeModule,
    HttpClientModule,
    CtSharedModule,
    CtEntityModule,
    RouterModule.forChild([
      {
        path: 'home',
        component: HomeComponent,
        data: {
          authorities: [],
          pageTitle: 'home.title'
        }
      }
    ])
  ],
  declarations: [LayoutComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent]
})
export class CtLayoutModule {}
