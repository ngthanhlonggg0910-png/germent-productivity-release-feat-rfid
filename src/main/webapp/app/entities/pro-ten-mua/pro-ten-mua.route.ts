import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProTenMua } from 'app/shared/model/pro-ten-mua.model';
import { ProTenMuaService } from './pro-ten-mua.service';
import { ProTenMuaComponent } from './pro-ten-mua.component';
import { ProTenMuaDetailComponent } from './pro-ten-mua-detail.component';
import { ProTenMuaUpdateComponent } from './pro-ten-mua-update.component';
import { ProTenMuaDeletePopupComponent } from './pro-ten-mua-delete-dialog.component';
import { IProTenMua } from 'app/shared/model/pro-ten-mua.model';

@Injectable({ providedIn: 'root' })
export class ProTenMuaResolve implements Resolve<IProTenMua> {
  constructor(private service: ProTenMuaService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProTenMua> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proTenMua: HttpResponse<ProTenMua>) => proTenMua.body));
    }
    return of(new ProTenMua());
  }
}

export const proTenMuaRoute: Routes = [
  {
    path: '',
    component: ProTenMuaComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proTenMua.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProTenMuaDetailComponent,
    resolve: {
      proTenMua: ProTenMuaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proTenMua.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProTenMuaUpdateComponent,
    resolve: {
      proTenMua: ProTenMuaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proTenMua.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProTenMuaUpdateComponent,
    resolve: {
      proTenMua: ProTenMuaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proTenMua.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proTenMuaPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProTenMuaDeletePopupComponent,
    resolve: {
      proTenMua: ProTenMuaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proTenMua.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
