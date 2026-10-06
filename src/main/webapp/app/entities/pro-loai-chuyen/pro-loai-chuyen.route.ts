import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProLoaiChuyen } from 'app/shared/model/pro-loai-chuyen.model';
import { ProLoaiChuyenService } from './pro-loai-chuyen.service';
import { ProLoaiChuyenComponent } from './pro-loai-chuyen.component';
import { ProLoaiChuyenDetailComponent } from './pro-loai-chuyen-detail.component';
import { ProLoaiChuyenUpdateComponent } from './pro-loai-chuyen-update.component';
import { ProLoaiChuyenDeletePopupComponent } from './pro-loai-chuyen-delete-dialog.component';
import { IProLoaiChuyen } from 'app/shared/model/pro-loai-chuyen.model';

@Injectable({ providedIn: 'root' })
export class ProLoaiChuyenResolve implements Resolve<IProLoaiChuyen> {
  constructor(private service: ProLoaiChuyenService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProLoaiChuyen> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proLoaiChuyen: HttpResponse<ProLoaiChuyen>) => proLoaiChuyen.body));
    }
    return of(new ProLoaiChuyen());
  }
}

export const proLoaiChuyenRoute: Routes = [
  {
    path: '',
    component: ProLoaiChuyenComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proLoaiChuyen.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProLoaiChuyenDetailComponent,
    resolve: {
      proLoaiChuyen: ProLoaiChuyenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proLoaiChuyen.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProLoaiChuyenUpdateComponent,
    resolve: {
      proLoaiChuyen: ProLoaiChuyenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proLoaiChuyen.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProLoaiChuyenUpdateComponent,
    resolve: {
      proLoaiChuyen: ProLoaiChuyenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proLoaiChuyen.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proLoaiChuyenPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProLoaiChuyenDeletePopupComponent,
    resolve: {
      proLoaiChuyen: ProLoaiChuyenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proLoaiChuyen.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
