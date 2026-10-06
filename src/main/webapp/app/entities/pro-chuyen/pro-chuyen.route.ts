import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProChuyen } from 'app/shared/model/pro-chuyen.model';
import { ProChuyenService } from './pro-chuyen.service';
import { ProChuyenComponent } from './pro-chuyen.component';
import { ProChuyenDetailComponent } from './pro-chuyen-detail.component';
import { ProChuyenUpdateComponent } from './pro-chuyen-update.component';
import { ProChuyenDeletePopupComponent } from './pro-chuyen-delete-dialog.component';
import { IProChuyen } from 'app/shared/model/pro-chuyen.model';

@Injectable({ providedIn: 'root' })
export class ProChuyenResolve implements Resolve<IProChuyen> {
  constructor(private service: ProChuyenService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProChuyen> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proChuyen: HttpResponse<ProChuyen>) => proChuyen.body));
    }
    return of(new ProChuyen());
  }
}

export const proChuyenRoute: Routes = [
  {
    path: '',
    component: ProChuyenComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proChuyen.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProChuyenDetailComponent,
    resolve: {
      proChuyen: ProChuyenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proChuyen.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProChuyenUpdateComponent,
    resolve: {
      proChuyen: ProChuyenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proChuyen.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProChuyenUpdateComponent,
    resolve: {
      proChuyen: ProChuyenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proChuyen.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proChuyenPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProChuyenDeletePopupComponent,
    resolve: {
      proChuyen: ProChuyenResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proChuyen.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
