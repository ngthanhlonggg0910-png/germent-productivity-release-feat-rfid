import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SysGroupModule } from 'app/shared/model/sys-group-module.model';
import { SysGroupModuleService } from './sys-group-module.service';
import { SysGroupModuleComponent } from './sys-group-module.component';
import { SysGroupModuleDetailComponent } from './sys-group-module-detail.component';
import { SysGroupModuleUpdateComponent } from './sys-group-module-update.component';
import { SysGroupModuleDeletePopupComponent } from './sys-group-module-delete-dialog.component';
import { ISysGroupModule } from 'app/shared/model/sys-group-module.model';

@Injectable({ providedIn: 'root' })
export class SysGroupModuleResolve implements Resolve<ISysGroupModule> {
  constructor(private service: SysGroupModuleService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysGroupModule> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((sysGroupModule: HttpResponse<SysGroupModule>) => sysGroupModule.body));
    }
    return of(new SysGroupModule());
  }
}

export const sysGroupModuleRoute: Routes = [
  {
    path: '',
    component: SysGroupModuleComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.sysGroupModule.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SysGroupModuleDetailComponent,
    resolve: {
      sysGroupModule: SysGroupModuleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysGroupModule.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SysGroupModuleUpdateComponent,
    resolve: {
      sysGroupModule: SysGroupModuleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysGroupModule.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SysGroupModuleUpdateComponent,
    resolve: {
      sysGroupModule: SysGroupModuleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysGroupModule.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const sysGroupModulePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SysGroupModuleDeletePopupComponent,
    resolve: {
      sysGroupModule: SysGroupModuleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysGroupModule.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
