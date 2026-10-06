import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ThongSoDoSPSize } from 'app/shared/model/thong-so-do-sp-size.model';
import { ThongSoDoSPSizeService } from './thong-so-do-sp-size.service';
import { ThongSoDoSPSizeComponent } from './thong-so-do-sp-size.component';
import { ThongSoDoSPSizeDetailComponent } from './thong-so-do-sp-size-detail.component';
import { ThongSoDoSPSizeUpdateComponent } from './thong-so-do-sp-size-update.component';
import { ThongSoDoSPSizeDeletePopupComponent } from './thong-so-do-sp-size-delete-dialog.component';
import { IThongSoDoSPSize } from 'app/shared/model/thong-so-do-sp-size.model';

@Injectable({ providedIn: 'root' })
export class ThongSoDoSPSizeResolve implements Resolve<IThongSoDoSPSize> {
  constructor(private service: ThongSoDoSPSizeService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IThongSoDoSPSize> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((thongSoDoSPSize: HttpResponse<ThongSoDoSPSize>) => thongSoDoSPSize.body));
    }
    return of(new ThongSoDoSPSize());
  }
}

export const thongSoDoSPSizeRoute: Routes = [
  {
    path: '',
    component: ThongSoDoSPSizeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.thongSoDoSPSize.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ThongSoDoSPSizeDetailComponent,
    resolve: {
      thongSoDoSPSize: ThongSoDoSPSizeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thongSoDoSPSize.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ThongSoDoSPSizeUpdateComponent,
    resolve: {
      thongSoDoSPSize: ThongSoDoSPSizeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thongSoDoSPSize.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ThongSoDoSPSizeUpdateComponent,
    resolve: {
      thongSoDoSPSize: ThongSoDoSPSizeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thongSoDoSPSize.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const thongSoDoSPSizePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ThongSoDoSPSizeDeletePopupComponent,
    resolve: {
      thongSoDoSPSize: ThongSoDoSPSizeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thongSoDoSPSize.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
