import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProChungLoaiSanPham } from 'app/shared/model/pro-chung-loai-san-pham.model';
import { ProChungLoaiSanPhamService } from './pro-chung-loai-san-pham.service';
import { ProChungLoaiSanPhamComponent } from './pro-chung-loai-san-pham.component';
import { ProChungLoaiSanPhamDetailComponent } from './pro-chung-loai-san-pham-detail.component';
import { ProChungLoaiSanPhamUpdateComponent } from './pro-chung-loai-san-pham-update.component';
import { ProChungLoaiSanPhamDeletePopupComponent } from './pro-chung-loai-san-pham-delete-dialog.component';
import { IProChungLoaiSanPham } from 'app/shared/model/pro-chung-loai-san-pham.model';

@Injectable({ providedIn: 'root' })
export class ProChungLoaiSanPhamResolve implements Resolve<IProChungLoaiSanPham> {
  constructor(private service: ProChungLoaiSanPhamService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProChungLoaiSanPham> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proChungLoaiSanPham: HttpResponse<ProChungLoaiSanPham>) => proChungLoaiSanPham.body));
    }
    return of(new ProChungLoaiSanPham());
  }
}

export const proChungLoaiSanPhamRoute: Routes = [
  {
    path: '',
    component: ProChungLoaiSanPhamComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proChungLoaiSanPham.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProChungLoaiSanPhamDetailComponent,
    resolve: {
      proChungLoaiSanPham: ProChungLoaiSanPhamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proChungLoaiSanPham.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProChungLoaiSanPhamUpdateComponent,
    resolve: {
      proChungLoaiSanPham: ProChungLoaiSanPhamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proChungLoaiSanPham.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProChungLoaiSanPhamUpdateComponent,
    resolve: {
      proChungLoaiSanPham: ProChungLoaiSanPhamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proChungLoaiSanPham.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proChungLoaiSanPhamPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProChungLoaiSanPhamDeletePopupComponent,
    resolve: {
      proChungLoaiSanPham: ProChungLoaiSanPhamResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proChungLoaiSanPham.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
