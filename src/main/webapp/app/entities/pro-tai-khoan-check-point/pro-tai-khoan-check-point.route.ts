import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProTaiKhoanCheckPoint } from 'app/shared/model/pro-tai-khoan-check-point.model';
import { ProTaiKhoanCheckPointService } from './pro-tai-khoan-check-point.service';
import { ProTaiKhoanCheckPointComponent } from './pro-tai-khoan-check-point.component';
import { ProTaiKhoanCheckPointDetailComponent } from './pro-tai-khoan-check-point-detail.component';
import { ProTaiKhoanCheckPointUpdateComponent } from './pro-tai-khoan-check-point-update.component';
import { ProTaiKhoanCheckPointDeletePopupComponent } from './pro-tai-khoan-check-point-delete-dialog.component';
import { IProTaiKhoanCheckPoint } from 'app/shared/model/pro-tai-khoan-check-point.model';

@Injectable({ providedIn: 'root' })
export class ProTaiKhoanCheckPointResolve implements Resolve<IProTaiKhoanCheckPoint> {
  constructor(private service: ProTaiKhoanCheckPointService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProTaiKhoanCheckPoint> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proTaiKhoanCheckPoint: HttpResponse<ProTaiKhoanCheckPoint>) => proTaiKhoanCheckPoint.body));
    }
    return of(new ProTaiKhoanCheckPoint());
  }
}

export const proTaiKhoanCheckPointRoute: Routes = [
  {
    path: '',
    component: ProTaiKhoanCheckPointComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proTaiKhoanCheckPoint.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProTaiKhoanCheckPointDetailComponent,
    resolve: {
      proTaiKhoanCheckPoint: ProTaiKhoanCheckPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proTaiKhoanCheckPoint.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProTaiKhoanCheckPointUpdateComponent,
    resolve: {
      proTaiKhoanCheckPoint: ProTaiKhoanCheckPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proTaiKhoanCheckPoint.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProTaiKhoanCheckPointUpdateComponent,
    resolve: {
      proTaiKhoanCheckPoint: ProTaiKhoanCheckPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proTaiKhoanCheckPoint.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proTaiKhoanCheckPointPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProTaiKhoanCheckPointDeletePopupComponent,
    resolve: {
      proTaiKhoanCheckPoint: ProTaiKhoanCheckPointResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proTaiKhoanCheckPoint.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
