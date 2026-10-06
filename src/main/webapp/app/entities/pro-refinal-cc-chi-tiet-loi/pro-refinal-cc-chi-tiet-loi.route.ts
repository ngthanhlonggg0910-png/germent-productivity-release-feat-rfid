import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProRefinalCCChiTietLoi } from 'app/shared/model/pro-refinal-cc-chi-tiet-loi.model';
import { ProRefinalCCChiTietLoiService } from './pro-refinal-cc-chi-tiet-loi.service';
import { ProRefinalCCChiTietLoiComponent } from './pro-refinal-cc-chi-tiet-loi.component';
import { ProRefinalCCChiTietLoiDetailComponent } from './pro-refinal-cc-chi-tiet-loi-detail.component';
import { ProRefinalCCChiTietLoiUpdateComponent } from './pro-refinal-cc-chi-tiet-loi-update.component';
import { ProRefinalCCChiTietLoiDeletePopupComponent } from './pro-refinal-cc-chi-tiet-loi-delete-dialog.component';
import { IProRefinalCCChiTietLoi } from 'app/shared/model/pro-refinal-cc-chi-tiet-loi.model';

@Injectable({ providedIn: 'root' })
export class ProRefinalCCChiTietLoiResolve implements Resolve<IProRefinalCCChiTietLoi> {
  constructor(private service: ProRefinalCCChiTietLoiService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProRefinalCCChiTietLoi> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proRefinalCCChiTietLoi: HttpResponse<ProRefinalCCChiTietLoi>) => proRefinalCCChiTietLoi.body));
    }
    return of(new ProRefinalCCChiTietLoi());
  }
}

export const proRefinalCCChiTietLoiRoute: Routes = [
  {
    path: '',
    component: ProRefinalCCChiTietLoiComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinalCCChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProRefinalCCChiTietLoiDetailComponent,
    resolve: {
      proRefinalCCChiTietLoi: ProRefinalCCChiTietLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinalCCChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProRefinalCCChiTietLoiUpdateComponent,
    resolve: {
      proRefinalCCChiTietLoi: ProRefinalCCChiTietLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinalCCChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProRefinalCCChiTietLoiUpdateComponent,
    resolve: {
      proRefinalCCChiTietLoi: ProRefinalCCChiTietLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinalCCChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proRefinalCCChiTietLoiPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProRefinalCCChiTietLoiDeletePopupComponent,
    resolve: {
      proRefinalCCChiTietLoi: ProRefinalCCChiTietLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinalCCChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
