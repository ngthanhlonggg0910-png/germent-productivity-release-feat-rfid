import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DoKimMd } from 'app/shared/model/do-kim-md.model';
import { DoKimMdService } from './do-kim-md.service';
import { DoKimMdComponent } from './do-kim-md.component';
import { DoKimMdDetailComponent } from './do-kim-md-detail.component';
import { DoKimMdUpdateComponent } from './do-kim-md-update.component';
import { DoKimMdDeletePopupComponent } from './do-kim-md-delete-dialog.component';
import { IDoKimMd } from 'app/shared/model/do-kim-md.model';

@Injectable({ providedIn: 'root' })
export class DoKimMdResolve implements Resolve<IDoKimMd> {
  constructor(private service: DoKimMdService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDoKimMd> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((doKimMd: HttpResponse<DoKimMd>) => doKimMd.body));
    }
    return of(new DoKimMd());
  }
}

export const doKimMdRoute: Routes = [
  {
    path: '',
    component: DoKimMdComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.doKimMd.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DoKimMdDetailComponent,
    resolve: {
      doKimMd: DoKimMdResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.doKimMd.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DoKimMdUpdateComponent,
    resolve: {
      doKimMd: DoKimMdResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.doKimMd.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DoKimMdUpdateComponent,
    resolve: {
      doKimMd: DoKimMdResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.doKimMd.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const doKimMdPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: DoKimMdDeletePopupComponent,
    resolve: {
      doKimMd: DoKimMdResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.doKimMd.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
