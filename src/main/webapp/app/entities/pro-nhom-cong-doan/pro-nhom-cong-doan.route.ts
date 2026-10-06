import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProNhomCongDoan } from 'app/shared/model/pro-nhom-cong-doan.model';
import { ProNhomCongDoanService } from './pro-nhom-cong-doan.service';
import { ProNhomCongDoanComponent } from './pro-nhom-cong-doan.component';
import { ProNhomCongDoanDetailComponent } from './pro-nhom-cong-doan-detail.component';
import { ProNhomCongDoanUpdateComponent } from './pro-nhom-cong-doan-update.component';
import { ProNhomCongDoanDeletePopupComponent } from './pro-nhom-cong-doan-delete-dialog.component';
import { IProNhomCongDoan } from 'app/shared/model/pro-nhom-cong-doan.model';

@Injectable({ providedIn: 'root' })
export class ProNhomCongDoanResolve implements Resolve<IProNhomCongDoan> {
  constructor(private service: ProNhomCongDoanService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProNhomCongDoan> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proNhomCongDoan: HttpResponse<ProNhomCongDoan>) => proNhomCongDoan.body));
    }
    return of(new ProNhomCongDoan());
  }
}

export const proNhomCongDoanRoute: Routes = [
  {
    path: '',
    component: ProNhomCongDoanComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proNhomCongDoan.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProNhomCongDoanDetailComponent,
    resolve: {
      proNhomCongDoan: ProNhomCongDoanResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proNhomCongDoan.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProNhomCongDoanUpdateComponent,
    resolve: {
      proNhomCongDoan: ProNhomCongDoanResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proNhomCongDoan.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProNhomCongDoanUpdateComponent,
    resolve: {
      proNhomCongDoan: ProNhomCongDoanResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proNhomCongDoan.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proNhomCongDoanPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProNhomCongDoanDeletePopupComponent,
    resolve: {
      proNhomCongDoan: ProNhomCongDoanResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proNhomCongDoan.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
