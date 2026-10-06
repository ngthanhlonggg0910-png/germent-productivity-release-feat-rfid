import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProRefinalCC } from 'app/shared/model/pro-refinal-cc.model';
import { ProRefinalCCService } from './pro-refinal-cc.service';
import { ProRefinalCCComponent } from './pro-refinal-cc.component';
import { ProRefinalCCDetailComponent } from './pro-refinal-cc-detail.component';
import { ProRefinalCCUpdateComponent } from './pro-refinal-cc-update.component';
import { ProRefinalCCDeletePopupComponent } from './pro-refinal-cc-delete-dialog.component';
import { IProRefinalCC } from 'app/shared/model/pro-refinal-cc.model';

@Injectable({ providedIn: 'root' })
export class ProRefinalCCResolve implements Resolve<IProRefinalCC> {
  constructor(private service: ProRefinalCCService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProRefinalCC> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proRefinalCC: HttpResponse<ProRefinalCC>) => proRefinalCC.body));
    }
    return of(new ProRefinalCC());
  }
}

export const proRefinalCCRoute: Routes = [
  {
    path: '',
    component: ProRefinalCCComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinalCC.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProRefinalCCDetailComponent,
    resolve: {
      proRefinalCC: ProRefinalCCResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinalCC.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProRefinalCCUpdateComponent,
    resolve: {
      proRefinalCC: ProRefinalCCResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinalCC.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProRefinalCCUpdateComponent,
    resolve: {
      proRefinalCC: ProRefinalCCResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinalCC.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proRefinalCCPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProRefinalCCDeletePopupComponent,
    resolve: {
      proRefinalCC: ProRefinalCCResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinalCC.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
