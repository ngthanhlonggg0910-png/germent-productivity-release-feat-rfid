import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProKhachHang } from 'app/shared/model/pro-khach-hang.model';
import { ProKhachHangService } from './pro-khach-hang.service';
import { ProKhachHangComponent } from './pro-khach-hang.component';
import { ProKhachHangDetailComponent } from './pro-khach-hang-detail.component';
import { ProKhachHangUpdateComponent } from './pro-khach-hang-update.component';
import { ProKhachHangDeletePopupComponent } from './pro-khach-hang-delete-dialog.component';
import { IProKhachHang } from 'app/shared/model/pro-khach-hang.model';

@Injectable({ providedIn: 'root' })
export class ProKhachHangResolve implements Resolve<IProKhachHang> {
  constructor(private service: ProKhachHangService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProKhachHang> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proKhachHang: HttpResponse<ProKhachHang>) => proKhachHang.body));
    }
    return of(new ProKhachHang());
  }
}

export const proKhachHangRoute: Routes = [
  {
    path: '',
    component: ProKhachHangComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proKhachHang.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProKhachHangDetailComponent,
    resolve: {
      proKhachHang: ProKhachHangResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proKhachHang.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProKhachHangUpdateComponent,
    resolve: {
      proKhachHang: ProKhachHangResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proKhachHang.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProKhachHangUpdateComponent,
    resolve: {
      proKhachHang: ProKhachHangResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proKhachHang.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proKhachHangPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProKhachHangDeletePopupComponent,
    resolve: {
      proKhachHang: ProKhachHangResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proKhachHang.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
