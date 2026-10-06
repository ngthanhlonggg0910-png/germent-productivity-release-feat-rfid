import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { errorRoute } from './layouts/error/error.route';
import { navbarRoute } from './layouts/navbar/navbar.route';
import { DEBUG_INFO_ENABLED } from 'app/app.constants';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { LayoutComponent } from './layouts/layout.component';
import { HomeComponent } from './home/home.component';

const LAYOUT_ROUTES = [navbarRoute, ...errorRoute];

@NgModule({
  imports: [
    RouterModule.forRoot(
      [
        {
          path: '',
          component: LayoutComponent,
          children: [
            { path: '', component: HomeComponent },
            {
              path: 'admin',
              data: {
                authorities: ['ROLE_ADMIN']
              },
              canActivate: [UserRouteAccessService],
              loadChildren: () => import('./admin/admin-routing.module').then(m => m.AdminRoutingModule)
            },
            {
              path: 'account',
              loadChildren: () => import('./account/account.module').then(m => m.CtAccountModule)
            },
            {
              path: 'sys',
              loadChildren: () => import('./entities/entity.module').then(m => m.CtEntityModule)
            }
          ]
        },
        {
          path: 'line/:id',
          loadChildren: () => import('./reports/line1/line1.module').then(m => m.CtLine1Module)
        },
        {
          path: 'line2/:id',
          loadChildren: () => import('./reports/line2/line2.module').then(m => m.CtLine2Module)
        },
        ...LAYOUT_ROUTES
      ],
      { enableTracing: DEBUG_INFO_ENABLED }
    )
  ],
  exports: [RouterModule]
})
export class CtAppRoutingModule {}
