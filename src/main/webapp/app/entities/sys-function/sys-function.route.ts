import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SysFunction } from 'app/shared/model/sys-function.model';
import { SysFunctionService } from './sys-function.service';
import { SysFunctionComponent } from './sys-function.component';
import { SysFunctionDetailComponent } from './sys-function-detail.component';
import { SysFunctionUpdateComponent } from './sys-function-update.component';
import { SysFunctionDeletePopupComponent } from './sys-function-delete-dialog.component';
import { ISysFunction } from 'app/shared/model/sys-function.model';

@Injectable({ providedIn: 'root' })
export class SysFunctionResolve implements Resolve<ISysFunction> {
  constructor(private service: SysFunctionService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysFunction> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((sysFunction: HttpResponse<SysFunction>) => sysFunction.body));
    }
    return of(new SysFunction());
  }
}

export const sysFunctionRoute: Routes = [
  {
    path: '',
    component: SysFunctionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.sysFunction.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SysFunctionDetailComponent,
    resolve: {
      sysFunction: SysFunctionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysFunction.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SysFunctionUpdateComponent,
    resolve: {
      sysFunction: SysFunctionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysFunction.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SysFunctionUpdateComponent,
    resolve: {
      sysFunction: SysFunctionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysFunction.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const sysFunctionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SysFunctionDeletePopupComponent,
    resolve: {
      sysFunction: SysFunctionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysFunction.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
