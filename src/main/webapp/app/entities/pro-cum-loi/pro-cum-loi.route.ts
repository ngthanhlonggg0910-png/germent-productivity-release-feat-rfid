import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProCumLoi } from 'app/shared/model/pro-cum-loi.model';
import { ProCumLoiService } from './pro-cum-loi.service';
import { ProCumLoiComponent } from './pro-cum-loi.component';
import { ProCumLoiDetailComponent } from './pro-cum-loi-detail.component';
import { ProCumLoiUpdateComponent } from './pro-cum-loi-update.component';
import { ProCumLoiDeletePopupComponent } from './pro-cum-loi-delete-dialog.component';
import { IProCumLoi } from 'app/shared/model/pro-cum-loi.model';

@Injectable({ providedIn: 'root' })
export class ProCumLoiResolve implements Resolve<IProCumLoi> {
  constructor(private service: ProCumLoiService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProCumLoi> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proCumLoi: HttpResponse<ProCumLoi>) => proCumLoi.body));
    }
    return of(new ProCumLoi());
  }
}

export const proCumLoiRoute: Routes = [
  {
    path: '',
    component: ProCumLoiComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proCumLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProCumLoiDetailComponent,
    resolve: {
      proCumLoi: ProCumLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proCumLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProCumLoiUpdateComponent,
    resolve: {
      proCumLoi: ProCumLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proCumLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProCumLoiUpdateComponent,
    resolve: {
      proCumLoi: ProCumLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proCumLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proCumLoiPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProCumLoiDeletePopupComponent,
    resolve: {
      proCumLoi: ProCumLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proCumLoi.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
