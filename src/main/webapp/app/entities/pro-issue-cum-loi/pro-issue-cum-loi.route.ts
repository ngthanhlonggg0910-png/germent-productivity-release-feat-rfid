import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProIssueCumLoi } from 'app/shared/model/pro-issue-cum-loi.model';
import { ProIssueCumLoiService } from './pro-issue-cum-loi.service';
import { ProIssueCumLoiComponent } from './pro-issue-cum-loi.component';
import { ProIssueCumLoiDetailComponent } from './pro-issue-cum-loi-detail.component';
import { ProIssueCumLoiUpdateComponent } from './pro-issue-cum-loi-update.component';
import { ProIssueCumLoiDeletePopupComponent } from './pro-issue-cum-loi-delete-dialog.component';
import { IProIssueCumLoi } from 'app/shared/model/pro-issue-cum-loi.model';

@Injectable({ providedIn: 'root' })
export class ProIssueCumLoiResolve implements Resolve<IProIssueCumLoi> {
  constructor(private service: ProIssueCumLoiService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProIssueCumLoi> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proIssueCumLoi: HttpResponse<ProIssueCumLoi>) => proIssueCumLoi.body));
    }
    return of(new ProIssueCumLoi());
  }
}

export const proIssueCumLoiRoute: Routes = [
  {
    path: '',
    component: ProIssueCumLoiComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proIssueCumLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProIssueCumLoiDetailComponent,
    resolve: {
      proIssueCumLoi: ProIssueCumLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proIssueCumLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProIssueCumLoiUpdateComponent,
    resolve: {
      proIssueCumLoi: ProIssueCumLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proIssueCumLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProIssueCumLoiUpdateComponent,
    resolve: {
      proIssueCumLoi: ProIssueCumLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proIssueCumLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proIssueCumLoiPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProIssueCumLoiDeletePopupComponent,
    resolve: {
      proIssueCumLoi: ProIssueCumLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proIssueCumLoi.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
