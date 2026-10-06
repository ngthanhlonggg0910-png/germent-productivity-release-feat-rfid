import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProNSCL, ProNSCL } from 'app/shared/model/pro-nscl.model';
import { ProNSCLService } from './pro-nscl.service';
import { ProNSCLComponent } from './pro-nscl.component';
import { ProNSCLDetailComponent } from './pro-nscl-detail.component';
import { ProNSCLUpdateComponent } from './pro-nscl-update.component';

@Injectable({ providedIn: 'root' })
export class ProNSCLResolve implements Resolve<IProNSCL> {
  constructor(private service: ProNSCLService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProNSCL> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((proNSCL: HttpResponse<ProNSCL>) => {
          if (proNSCL.body) {
            return of(proNSCL.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProNSCL());
  }
}

export const proNSCLRoute: Routes = [
  {
    path: '',
    component: ProNSCLComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proNSCL.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProNSCLDetailComponent,
    resolve: {
      proNSCL: ProNSCLResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proNSCL.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProNSCLUpdateComponent,
    resolve: {
      proNSCL: ProNSCLResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proNSCL.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProNSCLUpdateComponent,
    resolve: {
      proNSCL: ProNSCLResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proNSCL.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
