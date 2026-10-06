import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { NVButtonTrackingType } from 'app/shared/model/nv-button-tracking-type.model';
import { NVButtonTrackingTypeService } from './nv-button-tracking-type.service';
import { NVButtonTrackingTypeComponent } from './nv-button-tracking-type.component';
import { NVButtonTrackingTypeDetailComponent } from './nv-button-tracking-type-detail.component';
import { NVButtonTrackingTypeUpdateComponent } from './nv-button-tracking-type-update.component';
import { NVButtonTrackingTypeDeletePopupComponent } from './nv-button-tracking-type-delete-dialog.component';
import { INVButtonTrackingType } from 'app/shared/model/nv-button-tracking-type.model';

@Injectable({ providedIn: 'root' })
export class NVButtonTrackingTypeResolve implements Resolve<INVButtonTrackingType> {
  constructor(private service: NVButtonTrackingTypeService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INVButtonTrackingType> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((nVButtonTrackingType: HttpResponse<NVButtonTrackingType>) => nVButtonTrackingType.body));
    }
    return of(new NVButtonTrackingType());
  }
}

export const nVButtonTrackingTypeRoute: Routes = [
  {
    path: '',
    component: NVButtonTrackingTypeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.nVButtonTrackingType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: NVButtonTrackingTypeDetailComponent,
    resolve: {
      nVButtonTrackingType: NVButtonTrackingTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.nVButtonTrackingType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: NVButtonTrackingTypeUpdateComponent,
    resolve: {
      nVButtonTrackingType: NVButtonTrackingTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.nVButtonTrackingType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: NVButtonTrackingTypeUpdateComponent,
    resolve: {
      nVButtonTrackingType: NVButtonTrackingTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.nVButtonTrackingType.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const nVButtonTrackingTypePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: NVButtonTrackingTypeDeletePopupComponent,
    resolve: {
      nVButtonTrackingType: NVButtonTrackingTypeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.nVButtonTrackingType.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
