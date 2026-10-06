import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProRefinalChiTietLoi } from 'app/shared/model/pro-refinal-chi-tiet-loi.model';
import { ProRefinalChiTietLoiService } from './pro-refinal-chi-tiet-loi.service';
import { ProRefinalChiTietLoiComponent } from './pro-refinal-chi-tiet-loi.component';
import { ProRefinalChiTietLoiDetailComponent } from './pro-refinal-chi-tiet-loi-detail.component';
import { ProRefinalChiTietLoiUpdateComponent } from './pro-refinal-chi-tiet-loi-update.component';
import { ProRefinalChiTietLoiDeletePopupComponent } from './pro-refinal-chi-tiet-loi-delete-dialog.component';
import { IProRefinalChiTietLoi } from 'app/shared/model/pro-refinal-chi-tiet-loi.model';

@Injectable({ providedIn: 'root' })
export class ProRefinalChiTietLoiResolve implements Resolve<IProRefinalChiTietLoi> {
  constructor(private service: ProRefinalChiTietLoiService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProRefinalChiTietLoi> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proRefinalChiTietLoi: HttpResponse<ProRefinalChiTietLoi>) => proRefinalChiTietLoi.body));
    }
    return of(new ProRefinalChiTietLoi());
  }
}

export const proRefinalChiTietLoiRoute: Routes = [
  {
    path: '',
    component: ProRefinalChiTietLoiComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinalChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProRefinalChiTietLoiDetailComponent,
    resolve: {
      proRefinalChiTietLoi: ProRefinalChiTietLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinalChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProRefinalChiTietLoiUpdateComponent,
    resolve: {
      proRefinalChiTietLoi: ProRefinalChiTietLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinalChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProRefinalChiTietLoiUpdateComponent,
    resolve: {
      proRefinalChiTietLoi: ProRefinalChiTietLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinalChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proRefinalChiTietLoiPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProRefinalChiTietLoiDeletePopupComponent,
    resolve: {
      proRefinalChiTietLoi: ProRefinalChiTietLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proRefinalChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
