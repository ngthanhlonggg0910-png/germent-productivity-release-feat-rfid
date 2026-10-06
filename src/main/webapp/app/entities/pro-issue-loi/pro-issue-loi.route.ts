import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProIssueLoi } from 'app/shared/model/pro-issue-loi.model';
import { ProIssueLoiService } from './pro-issue-loi.service';
import { ProIssueLoiComponent } from './pro-issue-loi.component';
import { ProIssueLoiDetailComponent } from './pro-issue-loi-detail.component';
import { ProIssueLoiUpdateComponent } from './pro-issue-loi-update.component';
import { ProIssueLoiDeletePopupComponent } from './pro-issue-loi-delete-dialog.component';
import { IProIssueLoi } from 'app/shared/model/pro-issue-loi.model';

@Injectable({ providedIn: 'root' })
export class ProIssueLoiResolve implements Resolve<IProIssueLoi> {
  constructor(private service: ProIssueLoiService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProIssueLoi> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proIssueLoi: HttpResponse<ProIssueLoi>) => proIssueLoi.body));
    }
    return of(new ProIssueLoi());
  }
}

export const proIssueLoiRoute: Routes = [
  {
    path: '',
    component: ProIssueLoiComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proIssueLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProIssueLoiDetailComponent,
    resolve: {
      proIssueLoi: ProIssueLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proIssueLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProIssueLoiUpdateComponent,
    resolve: {
      proIssueLoi: ProIssueLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proIssueLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProIssueLoiUpdateComponent,
    resolve: {
      proIssueLoi: ProIssueLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proIssueLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proIssueLoiPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProIssueLoiDeletePopupComponent,
    resolve: {
      proIssueLoi: ProIssueLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proIssueLoi.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
