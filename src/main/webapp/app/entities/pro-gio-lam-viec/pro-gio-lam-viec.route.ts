import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProGioLamViec } from 'app/shared/model/pro-gio-lam-viec.model';
import { ProGioLamViecService } from './pro-gio-lam-viec.service';
import { ProGioLamViecComponent } from './pro-gio-lam-viec.component';
import { ProGioLamViecDetailComponent } from './pro-gio-lam-viec-detail.component';
import { ProGioLamViecUpdateComponent } from './pro-gio-lam-viec-update.component';
import { ProGioLamViecDeletePopupComponent } from './pro-gio-lam-viec-delete-dialog.component';
import { IProGioLamViec } from 'app/shared/model/pro-gio-lam-viec.model';

@Injectable({ providedIn: 'root' })
export class ProGioLamViecResolve implements Resolve<IProGioLamViec> {
  constructor(private service: ProGioLamViecService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProGioLamViec> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proGioLamViec: HttpResponse<ProGioLamViec>) => proGioLamViec.body));
    }
    return of(new ProGioLamViec());
  }
}

export const proGioLamViecRoute: Routes = [
  {
    path: '',
    component: ProGioLamViecComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proGioLamViec.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProGioLamViecDetailComponent,
    resolve: {
      proGioLamViec: ProGioLamViecResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proGioLamViec.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProGioLamViecUpdateComponent,
    resolve: {
      proGioLamViec: ProGioLamViecResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proGioLamViec.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProGioLamViecUpdateComponent,
    resolve: {
      proGioLamViec: ProGioLamViecResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proGioLamViec.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proGioLamViecPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProGioLamViecDeletePopupComponent,
    resolve: {
      proGioLamViec: ProGioLamViecResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proGioLamViec.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
