import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MapControlButton } from 'app/shared/model/map-control-button.model';
import { MapControlButtonService } from './map-control-button.service';
import { MapControlButtonComponent } from './map-control-button.component';
import { MapControlButtonDetailComponent } from './map-control-button-detail.component';
import { MapControlButtonUpdateComponent } from './map-control-button-update.component';
import { MapControlButtonDeletePopupComponent } from './map-control-button-delete-dialog.component';
import { IMapControlButton } from 'app/shared/model/map-control-button.model';

@Injectable({ providedIn: 'root' })
export class MapControlButtonResolve implements Resolve<IMapControlButton> {
  constructor(private service: MapControlButtonService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMapControlButton> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((mapControlButton: HttpResponse<MapControlButton>) => mapControlButton.body));
    }
    return of(new MapControlButton());
  }
}

export const mapControlButtonRoute: Routes = [
  {
    path: '',
    component: MapControlButtonComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.mapControlButton.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MapControlButtonDetailComponent,
    resolve: {
      mapControlButton: MapControlButtonResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.mapControlButton.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MapControlButtonUpdateComponent,
    resolve: {
      mapControlButton: MapControlButtonResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.mapControlButton.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MapControlButtonUpdateComponent,
    resolve: {
      mapControlButton: MapControlButtonResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.mapControlButton.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mapControlButtonPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MapControlButtonDeletePopupComponent,
    resolve: {
      mapControlButton: MapControlButtonResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.mapControlButton.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
