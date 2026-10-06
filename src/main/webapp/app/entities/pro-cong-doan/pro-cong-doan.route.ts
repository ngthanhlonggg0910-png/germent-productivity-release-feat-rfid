import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProCongDoan } from 'app/shared/model/pro-cong-doan.model';
import { ProCongDoanService } from './pro-cong-doan.service';
import { ProCongDoanComponent } from './pro-cong-doan.component';
import { ProCongDoanDetailComponent } from './pro-cong-doan-detail.component';
import { ProCongDoanUpdateComponent } from './pro-cong-doan-update.component';
import { ProCongDoanDeletePopupComponent } from './pro-cong-doan-delete-dialog.component';
import { IProCongDoan } from 'app/shared/model/pro-cong-doan.model';

@Injectable({ providedIn: 'root' })
export class ProCongDoanResolve implements Resolve<IProCongDoan> {
  constructor(private service: ProCongDoanService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProCongDoan> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proCongDoan: HttpResponse<ProCongDoan>) => proCongDoan.body));
    }
    return of(new ProCongDoan());
  }
}

export const proCongDoanRoute: Routes = [
  {
    path: '',
    component: ProCongDoanComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proCongDoan.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProCongDoanDetailComponent,
    resolve: {
      proCongDoan: ProCongDoanResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proCongDoan.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProCongDoanUpdateComponent,
    resolve: {
      proCongDoan: ProCongDoanResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proCongDoan.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProCongDoanUpdateComponent,
    resolve: {
      proCongDoan: ProCongDoanResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proCongDoan.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proCongDoanPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProCongDoanDeletePopupComponent,
    resolve: {
      proCongDoan: ProCongDoanResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proCongDoan.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
