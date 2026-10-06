import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProNSCLChiTietLoi } from 'app/shared/model/pro-nscl-chi-tiet-loi.model';
import { ProNSCLChiTietLoiService } from './pro-nscl-chi-tiet-loi.service';
import { ProNSCLChiTietLoiComponent } from './pro-nscl-chi-tiet-loi.component';
import { ProNSCLChiTietLoiDetailComponent } from './pro-nscl-chi-tiet-loi-detail.component';
import { ProNSCLChiTietLoiUpdateComponent } from './pro-nscl-chi-tiet-loi-update.component';
import { ProNSCLChiTietLoiDeletePopupComponent } from './pro-nscl-chi-tiet-loi-delete-dialog.component';
import { IProNSCLChiTietLoi } from 'app/shared/model/pro-nscl-chi-tiet-loi.model';

@Injectable({ providedIn: 'root' })
export class ProNSCLChiTietLoiResolve implements Resolve<IProNSCLChiTietLoi> {
  constructor(private service: ProNSCLChiTietLoiService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProNSCLChiTietLoi> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proNSCLChiTietLoi: HttpResponse<ProNSCLChiTietLoi>) => proNSCLChiTietLoi.body));
    }
    return of(new ProNSCLChiTietLoi());
  }
}

export const proNSCLChiTietLoiRoute: Routes = [
  {
    path: '',
    component: ProNSCLChiTietLoiComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proNSCLChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProNSCLChiTietLoiDetailComponent,
    resolve: {
      proNSCLChiTietLoi: ProNSCLChiTietLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proNSCLChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProNSCLChiTietLoiUpdateComponent,
    resolve: {
      proNSCLChiTietLoi: ProNSCLChiTietLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proNSCLChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProNSCLChiTietLoiUpdateComponent,
    resolve: {
      proNSCLChiTietLoi: ProNSCLChiTietLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proNSCLChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proNSCLChiTietLoiPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProNSCLChiTietLoiDeletePopupComponent,
    resolve: {
      proNSCLChiTietLoi: ProNSCLChiTietLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proNSCLChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
