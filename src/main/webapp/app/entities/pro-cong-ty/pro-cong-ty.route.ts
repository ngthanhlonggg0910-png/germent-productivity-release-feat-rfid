import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProCongTy } from 'app/shared/model/pro-cong-ty.model';
import { ProCongTyService } from './pro-cong-ty.service';
import { ProCongTyComponent } from './pro-cong-ty.component';
import { ProCongTyDetailComponent } from './pro-cong-ty-detail.component';
import { ProCongTyUpdateComponent } from './pro-cong-ty-update.component';
import { ProCongTyDeletePopupComponent } from './pro-cong-ty-delete-dialog.component';
import { IProCongTy } from 'app/shared/model/pro-cong-ty.model';

@Injectable({ providedIn: 'root' })
export class ProCongTyResolve implements Resolve<IProCongTy> {
  constructor(private service: ProCongTyService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProCongTy> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proCongTy: HttpResponse<ProCongTy>) => proCongTy.body));
    }
    return of(new ProCongTy());
  }
}

export const proCongTyRoute: Routes = [
  {
    path: '',
    component: ProCongTyComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proCongTy.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProCongTyDetailComponent,
    resolve: {
      proCongTy: ProCongTyResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proCongTy.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProCongTyUpdateComponent,
    resolve: {
      proCongTy: ProCongTyResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proCongTy.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProCongTyUpdateComponent,
    resolve: {
      proCongTy: ProCongTyResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proCongTy.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proCongTyPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProCongTyDeletePopupComponent,
    resolve: {
      proCongTy: ProCongTyResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proCongTy.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
