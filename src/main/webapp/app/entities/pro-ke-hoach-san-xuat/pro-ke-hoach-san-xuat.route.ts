import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ProKeHoachSanXuat } from 'app/shared/model/pro-ke-hoach-san-xuat.model';
import { ProKeHoachSanXuatService } from './pro-ke-hoach-san-xuat.service';
import { ProKeHoachSanXuatComponent } from './pro-ke-hoach-san-xuat.component';
import { ProKeHoachSanXuatDetailComponent } from './pro-ke-hoach-san-xuat-detail.component';
import { ProKeHoachSanXuatUpdateComponent } from './pro-ke-hoach-san-xuat-update.component';
import { ProKeHoachSanXuatDeletePopupComponent } from './pro-ke-hoach-san-xuat-delete-dialog.component';
import { IProKeHoachSanXuat } from 'app/shared/model/pro-ke-hoach-san-xuat.model';

@Injectable({ providedIn: 'root' })
export class ProKeHoachSanXuatResolve implements Resolve<IProKeHoachSanXuat> {
  constructor(private service: ProKeHoachSanXuatService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProKeHoachSanXuat> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((proKeHoachSanXuat: HttpResponse<ProKeHoachSanXuat>) => proKeHoachSanXuat.body));
    }
    return of(new ProKeHoachSanXuat());
  }
}

export const proKeHoachSanXuatRoute: Routes = [
  {
    path: '',
    component: ProKeHoachSanXuatComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.proKeHoachSanXuat.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProKeHoachSanXuatDetailComponent,
    resolve: {
      proKeHoachSanXuat: ProKeHoachSanXuatResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proKeHoachSanXuat.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProKeHoachSanXuatUpdateComponent,
    resolve: {
      proKeHoachSanXuat: ProKeHoachSanXuatResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proKeHoachSanXuat.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProKeHoachSanXuatUpdateComponent,
    resolve: {
      proKeHoachSanXuat: ProKeHoachSanXuatResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proKeHoachSanXuat.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const proKeHoachSanXuatPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ProKeHoachSanXuatDeletePopupComponent,
    resolve: {
      proKeHoachSanXuat: ProKeHoachSanXuatResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.proKeHoachSanXuat.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
