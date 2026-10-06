import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ThongSoDoSP } from 'app/shared/model/thong-so-do-sp.model';
import { ThongSoDoSPService } from './thong-so-do-sp.service';
import { ThongSoDoSPComponent } from './thong-so-do-sp.component';
import { ThongSoDoSPDetailComponent } from './thong-so-do-sp-detail.component';
import { ThongSoDoSPUpdateComponent } from './thong-so-do-sp-update.component';
import { ThongSoDoSPDeletePopupComponent } from './thong-so-do-sp-delete-dialog.component';
import { IThongSoDoSP } from 'app/shared/model/thong-so-do-sp.model';

@Injectable({ providedIn: 'root' })
export class ThongSoDoSPResolve implements Resolve<IThongSoDoSP> {
  constructor(private service: ThongSoDoSPService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IThongSoDoSP> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((thongSoDoSP: HttpResponse<ThongSoDoSP>) => thongSoDoSP.body));
    }
    return of(new ThongSoDoSP());
  }
}

export const thongSoDoSPRoute: Routes = [
  {
    path: '',
    component: ThongSoDoSPComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.thongSoDoSP.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ThongSoDoSPDetailComponent,
    resolve: {
      thongSoDoSP: ThongSoDoSPResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thongSoDoSP.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ThongSoDoSPUpdateComponent,
    resolve: {
      thongSoDoSP: ThongSoDoSPResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thongSoDoSP.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ThongSoDoSPUpdateComponent,
    resolve: {
      thongSoDoSP: ThongSoDoSPResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thongSoDoSP.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const thongSoDoSPPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ThongSoDoSPDeletePopupComponent,
    resolve: {
      thongSoDoSP: ThongSoDoSPResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thongSoDoSP.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
