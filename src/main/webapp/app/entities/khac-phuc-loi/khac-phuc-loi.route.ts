import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { KhacPhucLoi } from 'app/shared/model/khac-phuc-loi.model';
import { KhacPhucLoiService } from './khac-phuc-loi.service';
import { KhacPhucLoiComponent } from './khac-phuc-loi.component';
import { KhacPhucLoiDetailComponent } from './khac-phuc-loi-detail.component';
import { KhacPhucLoiUpdateComponent } from './khac-phuc-loi-update.component';
import { KhacPhucLoiDeletePopupComponent } from './khac-phuc-loi-delete-dialog.component';
import { IKhacPhucLoi } from 'app/shared/model/khac-phuc-loi.model';

@Injectable({ providedIn: 'root' })
export class KhacPhucLoiResolve implements Resolve<IKhacPhucLoi> {
  constructor(private service: KhacPhucLoiService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IKhacPhucLoi> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((khacPhucLoi: HttpResponse<KhacPhucLoi>) => khacPhucLoi.body));
    }
    return of(new KhacPhucLoi());
  }
}

export const khacPhucLoiRoute: Routes = [
  {
    path: '',
    component: KhacPhucLoiComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.khacPhucLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: KhacPhucLoiDetailComponent,
    resolve: {
      khacPhucLoi: KhacPhucLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.khacPhucLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: KhacPhucLoiUpdateComponent,
    resolve: {
      khacPhucLoi: KhacPhucLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.khacPhucLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: KhacPhucLoiUpdateComponent,
    resolve: {
      khacPhucLoi: KhacPhucLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.khacPhucLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const khacPhucLoiPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: KhacPhucLoiDeletePopupComponent,
    resolve: {
      khacPhucLoi: KhacPhucLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.khacPhucLoi.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
