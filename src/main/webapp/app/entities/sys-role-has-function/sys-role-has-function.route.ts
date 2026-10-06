import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SysRoleHasFunction } from 'app/shared/model/sys-role-has-function.model';
import { SysRoleHasFunctionService } from './sys-role-has-function.service';
import { SysRoleHasFunctionComponent } from './sys-role-has-function.component';
import { SysRoleHasFunctionDetailComponent } from './sys-role-has-function-detail.component';
import { SysRoleHasFunctionUpdateComponent } from './sys-role-has-function-update.component';
import { SysRoleHasFunctionDeletePopupComponent } from './sys-role-has-function-delete-dialog.component';
import { ISysRoleHasFunction } from 'app/shared/model/sys-role-has-function.model';

@Injectable({ providedIn: 'root' })
export class SysRoleHasFunctionResolve implements Resolve<ISysRoleHasFunction> {
  constructor(private service: SysRoleHasFunctionService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysRoleHasFunction> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((sysRoleHasFunction: HttpResponse<SysRoleHasFunction>) => sysRoleHasFunction.body));
    }
    return of(new SysRoleHasFunction());
  }
}

export const sysRoleHasFunctionRoute: Routes = [
  {
    path: '',
    component: SysRoleHasFunctionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.sysRoleHasFunction.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SysRoleHasFunctionDetailComponent,
    resolve: {
      sysRoleHasFunction: SysRoleHasFunctionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysRoleHasFunction.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SysRoleHasFunctionUpdateComponent,
    resolve: {
      sysRoleHasFunction: SysRoleHasFunctionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysRoleHasFunction.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SysRoleHasFunctionUpdateComponent,
    resolve: {
      sysRoleHasFunction: SysRoleHasFunctionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysRoleHasFunction.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const sysRoleHasFunctionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SysRoleHasFunctionDeletePopupComponent,
    resolve: {
      sysRoleHasFunction: SysRoleHasFunctionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysRoleHasFunction.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
