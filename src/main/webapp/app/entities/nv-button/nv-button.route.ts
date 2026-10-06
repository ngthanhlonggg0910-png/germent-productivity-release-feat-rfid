import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { NVButton } from 'app/shared/model/nv-button.model';
import { NVButtonService } from './nv-button.service';
import { NVButtonComponent } from './nv-button.component';
import { NVButtonDetailComponent } from './nv-button-detail.component';
import { NVButtonUpdateComponent } from './nv-button-update.component';
import { NVButtonDeletePopupComponent } from './nv-button-delete-dialog.component';
import { INVButton } from 'app/shared/model/nv-button.model';

@Injectable({ providedIn: 'root' })
export class NVButtonResolve implements Resolve<INVButton> {
  constructor(private service: NVButtonService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INVButton> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((nVButton: HttpResponse<NVButton>) => nVButton.body));
    }
    return of(new NVButton());
  }
}

export const nVButtonRoute: Routes = [
  {
    path: '',
    component: NVButtonComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.nVButton.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: NVButtonDetailComponent,
    resolve: {
      nVButton: NVButtonResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.nVButton.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: NVButtonUpdateComponent,
    resolve: {
      nVButton: NVButtonResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.nVButton.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: NVButtonUpdateComponent,
    resolve: {
      nVButton: NVButtonResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.nVButton.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const nVButtonPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: NVButtonDeletePopupComponent,
    resolve: {
      nVButton: NVButtonResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.nVButton.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
