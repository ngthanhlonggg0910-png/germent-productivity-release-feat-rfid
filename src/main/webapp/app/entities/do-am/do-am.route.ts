import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DoAm } from 'app/shared/model/do-am.model';
import { DoAmService } from './do-am.service';
import { DoAmComponent } from './do-am.component';
import { DoAmDetailComponent } from './do-am-detail.component';
import { DoAmUpdateComponent } from './do-am-update.component';
import { DoAmDeletePopupComponent } from './do-am-delete-dialog.component';
import { IDoAm } from 'app/shared/model/do-am.model';

@Injectable({ providedIn: 'root' })
export class DoAmResolve implements Resolve<IDoAm> {
  constructor(private service: DoAmService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDoAm> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((doAm: HttpResponse<DoAm>) => doAm.body));
    }
    return of(new DoAm());
  }
}

export const doAmRoute: Routes = [
  {
    path: '',
    component: DoAmComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.doAm.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DoAmDetailComponent,
    resolve: {
      doAm: DoAmResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.doAm.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DoAmUpdateComponent,
    resolve: {
      doAm: DoAmResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.doAm.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DoAmUpdateComponent,
    resolve: {
      doAm: DoAmResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.doAm.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const doAmPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: DoAmDeletePopupComponent,
    resolve: {
      doAm: DoAmResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.doAm.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
