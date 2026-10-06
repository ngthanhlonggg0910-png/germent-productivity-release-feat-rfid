import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ThongSoThucThi } from 'app/shared/model/thong-so-thuc-thi.model';
import { ThongSoThucThiService } from './thong-so-thuc-thi.service';
import { ThongSoThucThiComponent } from './thong-so-thuc-thi.component';
import { ThongSoThucThiDetailComponent } from './thong-so-thuc-thi-detail.component';
import { ThongSoThucThiUpdateComponent } from './thong-so-thuc-thi-update.component';
import { ThongSoThucThiDeletePopupComponent } from './thong-so-thuc-thi-delete-dialog.component';
import { IThongSoThucThi } from 'app/shared/model/thong-so-thuc-thi.model';

@Injectable({ providedIn: 'root' })
export class ThongSoThucThiResolve implements Resolve<IThongSoThucThi> {
  constructor(private service: ThongSoThucThiService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IThongSoThucThi> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((thongSoThucThi: HttpResponse<ThongSoThucThi>) => thongSoThucThi.body));
    }
    return of(new ThongSoThucThi());
  }
}

export const thongSoThucThiRoute: Routes = [
  {
    path: '',
    component: ThongSoThucThiComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.thongSoThucThi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ThongSoThucThiDetailComponent,
    resolve: {
      thongSoThucThi: ThongSoThucThiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thongSoThucThi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ThongSoThucThiUpdateComponent,
    resolve: {
      thongSoThucThi: ThongSoThucThiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thongSoThucThi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ThongSoThucThiUpdateComponent,
    resolve: {
      thongSoThucThi: ThongSoThucThiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thongSoThucThi.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const thongSoThucThiPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ThongSoThucThiDeletePopupComponent,
    resolve: {
      thongSoThucThi: ThongSoThucThiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thongSoThucThi.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
