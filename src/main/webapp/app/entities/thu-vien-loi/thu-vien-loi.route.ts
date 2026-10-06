import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ThuVienLoi } from 'app/shared/model/thu-vien-loi.model';
import { ThuVienLoiService } from './thu-vien-loi.service';
import { ThuVienLoiComponent } from './thu-vien-loi.component';
import { ThuVienLoiDetailComponent } from './thu-vien-loi-detail.component';
import { ThuVienLoiUpdateComponent } from './thu-vien-loi-update.component';
import { ThuVienLoiDeletePopupComponent } from './thu-vien-loi-delete-dialog.component';
import { IThuVienLoi } from 'app/shared/model/thu-vien-loi.model';

@Injectable({ providedIn: 'root' })
export class ThuVienLoiResolve implements Resolve<IThuVienLoi> {
  constructor(private service: ThuVienLoiService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IThuVienLoi> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((thuVienLoi: HttpResponse<ThuVienLoi>) => thuVienLoi.body));
    }
    return of(new ThuVienLoi());
  }
}

export const thuVienLoiRoute: Routes = [
  {
    path: '',
    component: ThuVienLoiComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.thuVienLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ThuVienLoiDetailComponent,
    resolve: {
      thuVienLoi: ThuVienLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thuVienLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ThuVienLoiUpdateComponent,
    resolve: {
      thuVienLoi: ThuVienLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thuVienLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ThuVienLoiUpdateComponent,
    resolve: {
      thuVienLoi: ThuVienLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thuVienLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const thuVienLoiPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ThuVienLoiDeletePopupComponent,
    resolve: {
      thuVienLoi: ThuVienLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thuVienLoi.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
