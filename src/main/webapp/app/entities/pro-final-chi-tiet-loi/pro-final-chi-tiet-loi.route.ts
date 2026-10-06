import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProFinalChiTietLoi } from 'app/shared/model/pro-final-chi-tiet-loi.model';
import { ProFinalChiTietLoiService } from './pro-final-chi-tiet-loi.service';
import { ProFinalChiTietLoiComponent } from './pro-final-chi-tiet-loi.component';
import { ProFinalChiTietLoiDetailComponent } from './pro-final-chi-tiet-loi-detail.component';
import { ProFinalChiTietLoiUpdateComponent } from './pro-final-chi-tiet-loi-update.component';
import { ProFinalChiTietLoiDeletePopupComponent } from './pro-final-chi-tiet-loi-delete-dialog.component';
import { IProFinalChiTietLoi } from 'app/shared/model/pro-final-chi-tiet-loi.model';

@Injectable({ providedIn: 'root' })
export class ProFinalChiTietLoiResolve implements Resolve<IProFinalChiTietLoi> {
  constructor(private service: ProFinalChiTietLoiService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProFinalChiTietLoi> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proFinalChiTietLoi: HttpResponse<ProFinalChiTietLoi>) => proFinalChiTietLoi.body));
    }
    return of(new ProFinalChiTietLoi());
  }
}

export const proFinalChiTietLoiRoute: Routes = [
  {
    path: '',
    component: ProFinalChiTietLoiComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proFinalChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProFinalChiTietLoiDetailComponent,
    resolve: {
      proFinalChiTietLoi: ProFinalChiTietLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proFinalChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProFinalChiTietLoiUpdateComponent,
    resolve: {
      proFinalChiTietLoi: ProFinalChiTietLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proFinalChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProFinalChiTietLoiUpdateComponent,
    resolve: {
      proFinalChiTietLoi: ProFinalChiTietLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proFinalChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proFinalChiTietLoiPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProFinalChiTietLoiDeletePopupComponent,
    resolve: {
      proFinalChiTietLoi: ProFinalChiTietLoiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proFinalChiTietLoi.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
