import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProAql } from 'app/shared/model/pro-aql.model';
import { ProAqlService } from './pro-aql.service';
import { ProAqlComponent } from './pro-aql.component';
import { ProAqlDetailComponent } from './pro-aql-detail.component';
import { ProAqlUpdateComponent } from './pro-aql-update.component';
import { ProAqlDeletePopupComponent } from './pro-aql-delete-dialog.component';
import { IProAql } from 'app/shared/model/pro-aql.model';

@Injectable({ providedIn: 'root' })
export class ProAqlResolve implements Resolve<IProAql> {
  constructor(private service: ProAqlService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProAql> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proAql: HttpResponse<ProAql>) => proAql.body));
    }
    return of(new ProAql());
  }
}

export const proAqlRoute: Routes = [
  {
    path: '',
    component: ProAqlComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proAql.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProAqlDetailComponent,
    resolve: {
      proAql: ProAqlResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proAql.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProAqlUpdateComponent,
    resolve: {
      proAql: ProAqlResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proAql.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProAqlUpdateComponent,
    resolve: {
      proAql: ProAqlResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proAql.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proAqlPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProAqlDeletePopupComponent,
    resolve: {
      proAql: ProAqlResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proAql.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
