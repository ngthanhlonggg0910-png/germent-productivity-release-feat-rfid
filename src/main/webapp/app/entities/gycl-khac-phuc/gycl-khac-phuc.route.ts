import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { GYCLKhacPhuc } from 'app/shared/model/gycl-khac-phuc.model';
import { GYCLKhacPhucService } from './gycl-khac-phuc.service';
import { GYCLKhacPhucComponent } from './gycl-khac-phuc.component';
import { GYCLKhacPhucDetailComponent } from './gycl-khac-phuc-detail.component';
import { GYCLKhacPhucUpdateComponent } from './gycl-khac-phuc-update.component';
import { GYCLKhacPhucDeletePopupComponent } from './gycl-khac-phuc-delete-dialog.component';
import { IGYCLKhacPhuc } from 'app/shared/model/gycl-khac-phuc.model';

@Injectable({ providedIn: 'root' })
export class GYCLKhacPhucResolve implements Resolve<IGYCLKhacPhuc> {
  constructor(private service: GYCLKhacPhucService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGYCLKhacPhuc> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((gYCLKhacPhuc: HttpResponse<GYCLKhacPhuc>) => gYCLKhacPhuc.body));
    }
    return of(new GYCLKhacPhuc());
  }
}

export const gYCLKhacPhucRoute: Routes = [
  {
    path: '',
    component: GYCLKhacPhucComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.gYCLKhacPhuc.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GYCLKhacPhucDetailComponent,
    resolve: {
      gYCLKhacPhuc: GYCLKhacPhucResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.gYCLKhacPhuc.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GYCLKhacPhucUpdateComponent,
    resolve: {
      gYCLKhacPhuc: GYCLKhacPhucResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.gYCLKhacPhuc.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GYCLKhacPhucUpdateComponent,
    resolve: {
      gYCLKhacPhuc: GYCLKhacPhucResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.gYCLKhacPhuc.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const gYCLKhacPhucPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GYCLKhacPhucDeletePopupComponent,
    resolve: {
      gYCLKhacPhuc: GYCLKhacPhucResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.gYCLKhacPhuc.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
