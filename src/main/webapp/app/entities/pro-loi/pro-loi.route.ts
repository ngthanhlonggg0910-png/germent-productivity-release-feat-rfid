import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProLoi } from 'app/shared/model/pro-loi.model';
import { ProLoiService } from './pro-loi.service';
import { ProLoiComponent } from './pro-loi.component';
import { ProLoiDetailComponent } from './pro-loi-detail.component';
import { ProLoiUpdateComponent } from './pro-loi-update.component';
import { ProLoiDeletePopupComponent } from './pro-loi-delete-dialog.component';
import { IProLoi } from 'app/shared/model/pro-loi.model';

@Injectable({ providedIn: 'root' })
export class ProLoiResolve implements Resolve<IProLoi> {
  constructor(private service: ProLoiService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProLoi> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proLoi: HttpResponse<ProLoi>) => proLoi.body));
    }
    return of(new ProLoi());
  }
}

export const proLoiRoute: Routes = [
  {
    path: '',
    component: ProLoiComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProLoiDetailComponent,
    resolve: {
      proLoi: ProLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProLoiUpdateComponent,
    resolve: {
      proLoi: ProLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProLoiUpdateComponent,
    resolve: {
      proLoi: ProLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proLoiPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProLoiDeletePopupComponent,
    resolve: {
      proLoi: ProLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proLoi.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
