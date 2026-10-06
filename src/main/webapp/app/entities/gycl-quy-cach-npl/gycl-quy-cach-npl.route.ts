import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { GYCLQuyCachNPL } from 'app/shared/model/gycl-quy-cach-npl.model';
import { GYCLQuyCachNPLService } from './gycl-quy-cach-npl.service';
import { GYCLQuyCachNPLComponent } from './gycl-quy-cach-npl.component';
import { GYCLQuyCachNPLDetailComponent } from './gycl-quy-cach-npl-detail.component';
import { GYCLQuyCachNPLUpdateComponent } from './gycl-quy-cach-npl-update.component';
import { GYCLQuyCachNPLDeletePopupComponent } from './gycl-quy-cach-npl-delete-dialog.component';
import { IGYCLQuyCachNPL } from 'app/shared/model/gycl-quy-cach-npl.model';

@Injectable({ providedIn: 'root' })
export class GYCLQuyCachNPLResolve implements Resolve<IGYCLQuyCachNPL> {
  constructor(private service: GYCLQuyCachNPLService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGYCLQuyCachNPL> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((gYCLQuyCachNPL: HttpResponse<GYCLQuyCachNPL>) => gYCLQuyCachNPL.body));
    }
    return of(new GYCLQuyCachNPL());
  }
}

export const gYCLQuyCachNPLRoute: Routes = [
  {
    path: '',
    component: GYCLQuyCachNPLComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.gYCLQuyCachNPL.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GYCLQuyCachNPLDetailComponent,
    resolve: {
      gYCLQuyCachNPL: GYCLQuyCachNPLResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.gYCLQuyCachNPL.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GYCLQuyCachNPLUpdateComponent,
    resolve: {
      gYCLQuyCachNPL: GYCLQuyCachNPLResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.gYCLQuyCachNPL.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GYCLQuyCachNPLUpdateComponent,
    resolve: {
      gYCLQuyCachNPL: GYCLQuyCachNPLResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.gYCLQuyCachNPL.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const gYCLQuyCachNPLPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GYCLQuyCachNPLDeletePopupComponent,
    resolve: {
      gYCLQuyCachNPL: GYCLQuyCachNPLResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.gYCLQuyCachNPL.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
