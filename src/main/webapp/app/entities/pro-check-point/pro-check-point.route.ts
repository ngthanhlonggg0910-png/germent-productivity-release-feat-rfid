import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProCheckPoint } from 'app/shared/model/pro-check-point.model';
import { ProCheckPointService } from './pro-check-point.service';
import { ProCheckPointComponent } from './pro-check-point.component';
import { ProCheckPointDetailComponent } from './pro-check-point-detail.component';
import { ProCheckPointUpdateComponent } from './pro-check-point-update.component';
import { ProCheckPointDeletePopupComponent } from './pro-check-point-delete-dialog.component';
import { IProCheckPoint } from 'app/shared/model/pro-check-point.model';

@Injectable({ providedIn: 'root' })
export class ProCheckPointResolve implements Resolve<IProCheckPoint> {
  constructor(private service: ProCheckPointService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProCheckPoint> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proCheckPoint: HttpResponse<ProCheckPoint>) => proCheckPoint.body));
    }
    return of(new ProCheckPoint());
  }
}

export const proCheckPointRoute: Routes = [
  {
    path: '',
    component: ProCheckPointComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proCheckPoint.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProCheckPointDetailComponent,
    resolve: {
      proCheckPoint: ProCheckPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proCheckPoint.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProCheckPointUpdateComponent,
    resolve: {
      proCheckPoint: ProCheckPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proCheckPoint.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProCheckPointUpdateComponent,
    resolve: {
      proCheckPoint: ProCheckPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proCheckPoint.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proCheckPointPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProCheckPointDeletePopupComponent,
    resolve: {
      proCheckPoint: ProCheckPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proCheckPoint.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
