import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ThongSoDo } from 'app/shared/model/thong-so-do.model';
import { ThongSoDoService } from './thong-so-do.service';
import { ThongSoDoComponent } from './thong-so-do.component';
import { ThongSoDoDetailComponent } from './thong-so-do-detail.component';
import { ThongSoDoUpdateComponent } from './thong-so-do-update.component';
import { ThongSoDoDeletePopupComponent } from './thong-so-do-delete-dialog.component';
import { IThongSoDo } from 'app/shared/model/thong-so-do.model';

@Injectable({ providedIn: 'root' })
export class ThongSoDoResolve implements Resolve<IThongSoDo> {
  constructor(private service: ThongSoDoService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IThongSoDo> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((thongSoDo: HttpResponse<ThongSoDo>) => thongSoDo.body));
    }
    return of(new ThongSoDo());
  }
}

export const thongSoDoRoute: Routes = [
  {
    path: '',
    component: ThongSoDoComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.thongSoDo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ThongSoDoDetailComponent,
    resolve: {
      thongSoDo: ThongSoDoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thongSoDo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ThongSoDoUpdateComponent,
    resolve: {
      thongSoDo: ThongSoDoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thongSoDo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ThongSoDoUpdateComponent,
    resolve: {
      thongSoDo: ThongSoDoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thongSoDo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const thongSoDoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ThongSoDoDeletePopupComponent,
    resolve: {
      thongSoDo: ThongSoDoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.thongSoDo.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
