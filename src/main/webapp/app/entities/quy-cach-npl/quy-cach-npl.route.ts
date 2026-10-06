import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { QuyCachNPL } from 'app/shared/model/quy-cach-npl.model';
import { QuyCachNPLService } from './quy-cach-npl.service';
import { QuyCachNPLComponent } from './quy-cach-npl.component';
import { QuyCachNPLDetailComponent } from './quy-cach-npl-detail.component';
import { QuyCachNPLUpdateComponent } from './quy-cach-npl-update.component';
import { QuyCachNPLDeletePopupComponent } from './quy-cach-npl-delete-dialog.component';
import { IQuyCachNPL } from 'app/shared/model/quy-cach-npl.model';

@Injectable({ providedIn: 'root' })
export class QuyCachNPLResolve implements Resolve<IQuyCachNPL> {
  constructor(private service: QuyCachNPLService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IQuyCachNPL> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((quyCachNPL: HttpResponse<QuyCachNPL>) => quyCachNPL.body));
    }
    return of(new QuyCachNPL());
  }
}

export const quyCachNPLRoute: Routes = [
  {
    path: '',
    component: QuyCachNPLComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.quyCachNPL.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: QuyCachNPLDetailComponent,
    resolve: {
      quyCachNPL: QuyCachNPLResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.quyCachNPL.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: QuyCachNPLUpdateComponent,
    resolve: {
      quyCachNPL: QuyCachNPLResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.quyCachNPL.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: QuyCachNPLUpdateComponent,
    resolve: {
      quyCachNPL: QuyCachNPLResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.quyCachNPL.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const quyCachNPLPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: QuyCachNPLDeletePopupComponent,
    resolve: {
      quyCachNPL: QuyCachNPLResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.quyCachNPL.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
