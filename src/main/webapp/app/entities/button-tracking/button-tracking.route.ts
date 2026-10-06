import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ButtonTracking } from 'app/shared/model/button-tracking.model';
import { ButtonTrackingService } from './button-tracking.service';
import { ButtonTrackingComponent } from './button-tracking.component';
import { ButtonTrackingDetailComponent } from './button-tracking-detail.component';
import { ButtonTrackingUpdateComponent } from './button-tracking-update.component';
import { ButtonTrackingDeletePopupComponent } from './button-tracking-delete-dialog.component';
import { IButtonTracking } from 'app/shared/model/button-tracking.model';

@Injectable({ providedIn: 'root' })
export class ButtonTrackingResolve implements Resolve<IButtonTracking> {
  constructor(private service: ButtonTrackingService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IButtonTracking> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((buttonTracking: HttpResponse<ButtonTracking>) => buttonTracking.body));
    }
    return of(new ButtonTracking());
  }
}

export const buttonTrackingRoute: Routes = [
  {
    path: '',
    component: ButtonTrackingComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.buttonTracking.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ButtonTrackingDetailComponent,
    resolve: {
      buttonTracking: ButtonTrackingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.buttonTracking.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ButtonTrackingUpdateComponent,
    resolve: {
      buttonTracking: ButtonTrackingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.buttonTracking.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ButtonTrackingUpdateComponent,
    resolve: {
      buttonTracking: ButtonTrackingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.buttonTracking.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const buttonTrackingPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ButtonTrackingDeletePopupComponent,
    resolve: {
      buttonTracking: ButtonTrackingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.buttonTracking.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
