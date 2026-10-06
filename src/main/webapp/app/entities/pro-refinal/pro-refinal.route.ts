import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProRefinal } from 'app/shared/model/pro-refinal.model';
import { ProRefinalService } from './pro-refinal.service';
import { ProRefinalComponent } from './pro-refinal.component';
import { ProRefinalDetailComponent } from './pro-refinal-detail.component';
import { ProRefinalUpdateComponent } from './pro-refinal-update.component';
import { ProRefinalDeletePopupComponent } from './pro-refinal-delete-dialog.component';
import { IProRefinal } from 'app/shared/model/pro-refinal.model';

@Injectable({ providedIn: 'root' })
export class ProRefinalResolve implements Resolve<IProRefinal> {
  constructor(private service: ProRefinalService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProRefinal> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proRefinal: HttpResponse<ProRefinal>) => proRefinal.body));
    }
    return of(new ProRefinal());
  }
}

export const proRefinalRoute: Routes = [
  {
    path: '',
    component: ProRefinalComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinal.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProRefinalDetailComponent,
    resolve: {
      proRefinal: ProRefinalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinal.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProRefinalUpdateComponent,
    resolve: {
      proRefinal: ProRefinalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinal.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProRefinalUpdateComponent,
    resolve: {
      proRefinal: ProRefinalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinal.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proRefinalPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProRefinalDeletePopupComponent,
    resolve: {
      proRefinal: ProRefinalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinal.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
