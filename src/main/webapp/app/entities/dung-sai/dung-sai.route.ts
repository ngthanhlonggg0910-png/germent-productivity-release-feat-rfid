import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { DungSai } from 'app/shared/model/dung-sai.model';
import { DungSaiService } from './dung-sai.service';
import { DungSaiComponent } from './dung-sai.component';
import { DungSaiDetailComponent } from './dung-sai-detail.component';
import { DungSaiUpdateComponent } from './dung-sai-update.component';
import { DungSaiDeletePopupComponent } from './dung-sai-delete-dialog.component';
import { IDungSai } from 'app/shared/model/dung-sai.model';

@Injectable({ providedIn: 'root' })
export class DungSaiResolve implements Resolve<IDungSai> {
  constructor(private service: DungSaiService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDungSai> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((dungSai: HttpResponse<DungSai>) => dungSai.body));
    }
    return of(new DungSai());
  }
}

export const dungSaiRoute: Routes = [
  {
    path: '',
    component: DungSaiComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.dungSai.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DungSaiDetailComponent,
    resolve: {
      dungSai: DungSaiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.dungSai.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DungSaiUpdateComponent,
    resolve: {
      dungSai: DungSaiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.dungSai.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DungSaiUpdateComponent,
    resolve: {
      dungSai: DungSaiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.dungSai.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const dungSaiPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: DungSaiDeletePopupComponent,
    resolve: {
      dungSai: DungSaiResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.dungSai.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
