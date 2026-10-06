import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProNhaMay } from 'app/shared/model/pro-nha-may.model';
import { ProNhaMayService } from './pro-nha-may.service';
import { ProNhaMayComponent } from './pro-nha-may.component';
import { ProNhaMayDetailComponent } from './pro-nha-may-detail.component';
import { ProNhaMayUpdateComponent } from './pro-nha-may-update.component';
import { ProNhaMayDeletePopupComponent } from './pro-nha-may-delete-dialog.component';
import { IProNhaMay } from 'app/shared/model/pro-nha-may.model';

@Injectable({ providedIn: 'root' })
export class ProNhaMayResolve implements Resolve<IProNhaMay> {
  constructor(private service: ProNhaMayService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProNhaMay> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proNhaMay: HttpResponse<ProNhaMay>) => proNhaMay.body));
    }
    return of(new ProNhaMay());
  }
}

export const proNhaMayRoute: Routes = [
  {
    path: '',
    component: ProNhaMayComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proNhaMay.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProNhaMayDetailComponent,
    resolve: {
      proNhaMay: ProNhaMayResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proNhaMay.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProNhaMayUpdateComponent,
    resolve: {
      proNhaMay: ProNhaMayResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proNhaMay.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProNhaMayUpdateComponent,
    resolve: {
      proNhaMay: ProNhaMayResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proNhaMay.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proNhaMayPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProNhaMayDeletePopupComponent,
    resolve: {
      proNhaMay: ProNhaMayResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proNhaMay.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
