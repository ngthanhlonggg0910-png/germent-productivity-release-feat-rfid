import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProSanPham } from 'app/shared/model/pro-san-pham.model';
import { ProSanPhamService } from './pro-san-pham.service';
import { ProSanPhamComponent } from './pro-san-pham.component';
import { ProSanPhamDetailComponent } from './pro-san-pham-detail.component';
import { ProSanPhamUpdateComponent } from './pro-san-pham-update.component';
import { ProSanPhamDeletePopupComponent } from './pro-san-pham-delete-dialog.component';
import { IProSanPham } from 'app/shared/model/pro-san-pham.model';

@Injectable({ providedIn: 'root' })
export class ProSanPhamResolve implements Resolve<IProSanPham> {
  constructor(private service: ProSanPhamService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProSanPham> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proSanPham: HttpResponse<ProSanPham>) => proSanPham.body));
    }
    return of(new ProSanPham());
  }
}

export const proSanPhamRoute: Routes = [
  {
    path: '',
    component: ProSanPhamComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proSanPham.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProSanPhamDetailComponent,
    resolve: {
      proSanPham: ProSanPhamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proSanPham.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProSanPhamUpdateComponent,
    resolve: {
      proSanPham: ProSanPhamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proSanPham.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProSanPhamUpdateComponent,
    resolve: {
      proSanPham: ProSanPhamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proSanPham.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proSanPhamPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProSanPhamDeletePopupComponent,
    resolve: {
      proSanPham: ProSanPhamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proSanPham.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
