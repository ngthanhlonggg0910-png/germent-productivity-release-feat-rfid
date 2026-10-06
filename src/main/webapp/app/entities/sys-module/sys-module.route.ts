import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SysModule } from 'app/shared/model/sys-module.model';
import { SysModuleService } from './sys-module.service';
import { SysModuleComponent } from './sys-module.component';
import { SysModuleDetailComponent } from './sys-module-detail.component';
import { SysModuleUpdateComponent } from './sys-module-update.component';
import { SysModuleDeletePopupComponent } from './sys-module-delete-dialog.component';
import { ISysModule } from 'app/shared/model/sys-module.model';

@Injectable({ providedIn: 'root' })
export class SysModuleResolve implements Resolve<ISysModule> {
  constructor(private service: SysModuleService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysModule> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((sysModule: HttpResponse<SysModule>) => sysModule.body));
    }
    return of(new SysModule());
  }
}

export const sysModuleRoute: Routes = [
  {
    path: '',
    component: SysModuleComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.sysModule.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SysModuleDetailComponent,
    resolve: {
      sysModule: SysModuleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysModule.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SysModuleUpdateComponent,
    resolve: {
      sysModule: SysModuleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysModule.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SysModuleUpdateComponent,
    resolve: {
      sysModule: SysModuleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysModule.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const sysModulePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SysModuleDeletePopupComponent,
    resolve: {
      sysModule: SysModuleResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysModule.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
