import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SysPermission } from 'app/shared/model/sys-permission.model';
import { SysPermissionService } from './sys-permission.service';
import { SysPermissionComponent } from './sys-permission.component';
import { SysPermissionDetailComponent } from './sys-permission-detail.component';
import { SysPermissionUpdateComponent } from './sys-permission-update.component';
import { SysPermissionDeletePopupComponent } from './sys-permission-delete-dialog.component';
import { ISysPermission } from 'app/shared/model/sys-permission.model';

@Injectable({ providedIn: 'root' })
export class SysPermissionResolve implements Resolve<ISysPermission> {
  constructor(private service: SysPermissionService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysPermission> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((sysPermission: HttpResponse<SysPermission>) => sysPermission.body));
    }
    return of(new SysPermission());
  }
}

export const sysPermissionRoute: Routes = [
  {
    path: '',
    component: SysPermissionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.sysPermission.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SysPermissionDetailComponent,
    resolve: {
      sysPermission: SysPermissionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysPermission.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SysPermissionUpdateComponent,
    resolve: {
      sysPermission: SysPermissionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysPermission.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SysPermissionUpdateComponent,
    resolve: {
      sysPermission: SysPermissionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysPermission.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const sysPermissionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SysPermissionDeletePopupComponent,
    resolve: {
      sysPermission: SysPermissionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysPermission.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
