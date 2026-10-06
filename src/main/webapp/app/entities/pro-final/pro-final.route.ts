import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProFinal } from 'app/shared/model/pro-final.model';
import { ProFinalService } from './pro-final.service';
import { ProFinalComponent } from './pro-final.component';
import { ProFinalDetailComponent } from './pro-final-detail.component';
import { ProFinalUpdateComponent } from './pro-final-update.component';
import { ProFinalDeletePopupComponent } from './pro-final-delete-dialog.component';
import { IProFinal } from 'app/shared/model/pro-final.model';

@Injectable({ providedIn: 'root' })
export class ProFinalResolve implements Resolve<IProFinal> {
  constructor(private service: ProFinalService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProFinal> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proFinal: HttpResponse<ProFinal>) => proFinal.body));
    }
    return of(new ProFinal());
  }
}

export const proFinalRoute: Routes = [
  {
    path: '',
    component: ProFinalComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proFinal.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProFinalDetailComponent,
    resolve: {
      proFinal: ProFinalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proFinal.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProFinalUpdateComponent,
    resolve: {
      proFinal: ProFinalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proFinal.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProFinalUpdateComponent,
    resolve: {
      proFinal: ProFinalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proFinal.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proFinalPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProFinalDeletePopupComponent,
    resolve: {
      proFinal: ProFinalResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proFinal.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
