import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { CauHinhZalo } from 'app/shared/model/cau-hinh-zalo.model';
import { CauHinhZaloService } from './cau-hinh-zalo.service';
import { CauHinhZaloComponent } from './cau-hinh-zalo.component';
import { CauHinhZaloDetailComponent } from './cau-hinh-zalo-detail.component';
import { ICauHinhZalo } from 'app/shared/model/cau-hinh-zalo.model';

@Injectable({ providedIn: 'root' })
export class CauHinhZaloResolve implements Resolve<ICauHinhZalo> {
  constructor(private service: CauHinhZaloService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICauHinhZalo> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((cauHinhZalo: HttpResponse<CauHinhZalo>) => cauHinhZalo.body));
    }
    return of(new CauHinhZalo());
  }
}

export const cauHinhZaloRoute: Routes = [
  {
    path: '',
    component: CauHinhZaloComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.cauHinhZalo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CauHinhZaloDetailComponent,
    resolve: {
      cauHinhZalo: CauHinhZaloResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.cauHinhZalo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const cauHinhZaloPopupRoute: Routes = [];
