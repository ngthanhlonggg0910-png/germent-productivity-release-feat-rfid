import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MapButtonAction } from 'app/shared/model/map-button-action.model';
import { MapButtonActionService } from './map-button-action.service';
import { MapButtonActionComponent } from './map-button-action.component';
import { MapButtonActionDetailComponent } from './map-button-action-detail.component';
import { MapButtonActionUpdateComponent } from './map-button-action-update.component';
import { MapButtonActionDeletePopupComponent } from './map-button-action-delete-dialog.component';
import { IMapButtonAction } from 'app/shared/model/map-button-action.model';

@Injectable({ providedIn: 'root' })
export class MapButtonActionResolve implements Resolve<IMapButtonAction> {
  constructor(private service: MapButtonActionService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMapButtonAction> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((mapButtonAction: HttpResponse<MapButtonAction>) => mapButtonAction.body));
    }
    return of(new MapButtonAction());
  }
}

export const mapButtonActionRoute: Routes = [
  {
    path: '',
    component: MapButtonActionComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.mapButtonAction.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MapButtonActionDetailComponent,
    resolve: {
      mapButtonAction: MapButtonActionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.mapButtonAction.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MapButtonActionUpdateComponent,
    resolve: {
      mapButtonAction: MapButtonActionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.mapButtonAction.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MapButtonActionUpdateComponent,
    resolve: {
      mapButtonAction: MapButtonActionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.mapButtonAction.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mapButtonActionPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MapButtonActionDeletePopupComponent,
    resolve: {
      mapButtonAction: MapButtonActionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.mapButtonAction.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
