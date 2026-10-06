import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SysFile } from 'app/shared/model/sys-file.model';
import { SysFileService } from './sys-file.service';
import { SysFileComponent } from './sys-file.component';
import { SysFileDetailComponent } from './sys-file-detail.component';
import { SysFileUpdateComponent } from './sys-file-update.component';
import { SysFileDeletePopupComponent } from './sys-file-delete-dialog.component';
import { ISysFile } from 'app/shared/model/sys-file.model';

@Injectable({ providedIn: 'root' })
export class SysFileResolve implements Resolve<ISysFile> {
  constructor(private service: SysFileService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISysFile> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((sysFile: HttpResponse<SysFile>) => sysFile.body));
    }
    return of(new SysFile());
  }
}

export const sysFileRoute: Routes = [
  {
    path: '',
    component: SysFileComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.sysFile.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SysFileDetailComponent,
    resolve: {
      sysFile: SysFileResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysFile.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SysFileUpdateComponent,
    resolve: {
      sysFile: SysFileResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysFile.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SysFileUpdateComponent,
    resolve: {
      sysFile: SysFileResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysFile.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const sysFilePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: SysFileDeletePopupComponent,
    resolve: {
      sysFile: SysFileResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.sysFile.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
