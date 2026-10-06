import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Tracking } from 'app/shared/model/tracking.model';
import { TrackingService } from './tracking.service';
import { TrackingComponent } from './tracking.component';
import { TrackingDetailComponent } from './tracking-detail.component';
import { TrackingUpdateComponent } from './tracking-update.component';
import { TrackingDeletePopupComponent } from './tracking-delete-dialog.component';
import { ITracking } from 'app/shared/model/tracking.model';

@Injectable({ providedIn: 'root' })
export class TrackingResolve implements Resolve<ITracking> {
  constructor(private service: TrackingService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITracking> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((tracking: HttpResponse<Tracking>) => tracking.body));
    }
    return of(new Tracking());
  }
}

export const trackingRoute: Routes = [
  {
    path: '',
    component: TrackingComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.tracking.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TrackingDetailComponent,
    resolve: {
      tracking: TrackingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.tracking.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TrackingUpdateComponent,
    resolve: {
      tracking: TrackingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.tracking.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TrackingUpdateComponent,
    resolve: {
      tracking: TrackingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.tracking.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const trackingPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TrackingDeletePopupComponent,
    resolve: {
      tracking: TrackingResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.tracking.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
