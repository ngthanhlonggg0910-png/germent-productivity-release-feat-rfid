import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { LibSoLuongPoKiem } from 'app/shared/model/lib-so-luong-po-kiem.model';
import { LibSoLuongPoKiemService } from './lib-so-luong-po-kiem.service';
import { LibSoLuongPoKiemComponent } from './lib-so-luong-po-kiem.component';
import { LibSoLuongPoKiemDetailComponent } from './lib-so-luong-po-kiem-detail.component';
import { LibSoLuongPoKiemUpdateComponent } from './lib-so-luong-po-kiem-update.component';
import { LibSoLuongPoKiemDeletePopupComponent } from './lib-so-luong-po-kiem-delete-dialog.component';
import { ILibSoLuongPoKiem } from 'app/shared/model/lib-so-luong-po-kiem.model';

@Injectable({ providedIn: 'root' })
export class LibSoLuongPoKiemResolve implements Resolve<ILibSoLuongPoKiem> {
  constructor(private service: LibSoLuongPoKiemService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILibSoLuongPoKiem> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((libSoLuongPoKiem: HttpResponse<LibSoLuongPoKiem>) => libSoLuongPoKiem.body));
    }
    return of(new LibSoLuongPoKiem());
  }
}

export const libSoLuongPoKiemRoute: Routes = [
  {
    path: '',
    component: LibSoLuongPoKiemComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.libSoLuongPoKiem.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LibSoLuongPoKiemDetailComponent,
    resolve: {
      libSoLuongPoKiem: LibSoLuongPoKiemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.libSoLuongPoKiem.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LibSoLuongPoKiemUpdateComponent,
    resolve: {
      libSoLuongPoKiem: LibSoLuongPoKiemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.libSoLuongPoKiem.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LibSoLuongPoKiemUpdateComponent,
    resolve: {
      libSoLuongPoKiem: LibSoLuongPoKiemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.libSoLuongPoKiem.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const libSoLuongPoKiemPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: LibSoLuongPoKiemDeletePopupComponent,
    resolve: {
      libSoLuongPoKiem: LibSoLuongPoKiemResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.libSoLuongPoKiem.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
