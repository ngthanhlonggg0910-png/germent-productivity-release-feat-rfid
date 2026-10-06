import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SoLuongHangKiem } from 'app/shared/model/so-luong-hang-kiem.model';
import { SoLuongHangKiemService } from './so-luong-hang-kiem.service';
import { SoLuongHangKiemComponent } from './so-luong-hang-kiem.component';
import { SoLuongHangKiemDetailComponent } from './so-luong-hang-kiem-detail.component';
import { SoLuongHangKiemUpdateComponent } from './so-luong-hang-kiem-update.component';
import { SoLuongHangKiemDeletePopupComponent } from './so-luong-hang-kiem-delete-dialog.component';
import { ISoLuongHangKiem } from 'app/shared/model/so-luong-hang-kiem.model';

@Injectable({ providedIn: 'root' })
export class SoLuongHangKiemResolve implements Resolve<ISoLuongHangKiem> {
  constructor(private service: SoLuongHangKiemService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISoLuongHangKiem> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((soLuongHangKiem: HttpResponse<SoLuongHangKiem>) => soLuongHangKiem.body));
    }
    return of(new SoLuongHangKiem());
  }
}

export const soLuongHangKiemRoute: Routes = [
  {
    path: '',
    component: SoLuongHangKiemComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.soLuongHangKiem.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SoLuongHangKiemDetailComponent,
    resolve: {
      soLuongHangKiem: SoLuongHangKiemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.soLuongHangKiem.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SoLuongHangKiemUpdateComponent,
    resolve: {
      soLuongHangKiem: SoLuongHangKiemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.soLuongHangKiem.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SoLuongHangKiemUpdateComponent,
    resolve: {
      soLuongHangKiem: SoLuongHangKiemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.soLuongHangKiem.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const soLuongHangKiemPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SoLuongHangKiemDeletePopupComponent,
    resolve: {
      soLuongHangKiem: SoLuongHangKiemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.soLuongHangKiem.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
