import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProIssue } from 'app/shared/model/pro-issue.model';
import { ProIssueService } from './pro-issue.service';
import { ProIssueComponent } from './pro-issue.component';
import { ProIssueDetailComponent } from './pro-issue-detail.component';
import { ProIssueUpdateComponent } from './pro-issue-update.component';
import { ProIssueDeletePopupComponent } from './pro-issue-delete-dialog.component';
import { IProIssue } from 'app/shared/model/pro-issue.model';

@Injectable({ providedIn: 'root' })
export class ProIssueResolve implements Resolve<IProIssue> {
  constructor(private service: ProIssueService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProIssue> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proIssue: HttpResponse<ProIssue>) => proIssue.body));
    }
    return of(new ProIssue());
  }
}

export const proIssueRoute: Routes = [
  {
    path: '',
    component: ProIssueComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proIssue.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProIssueDetailComponent,
    resolve: {
      proIssue: ProIssueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proIssue.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProIssueUpdateComponent,
    resolve: {
      proIssue: ProIssueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proIssue.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProIssueUpdateComponent,
    resolve: {
      proIssue: ProIssueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proIssue.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proIssuePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProIssueDeletePopupComponent,
    resolve: {
      proIssue: ProIssueResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proIssue.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
