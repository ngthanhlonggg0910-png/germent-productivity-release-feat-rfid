import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { GopYChatLuong } from 'app/shared/model/gop-y-chat-luong.model';
import { GopYChatLuongService } from './gop-y-chat-luong.service';
import { GopYChatLuongComponent } from './gop-y-chat-luong.component';
import { GopYChatLuongDetailComponent } from './gop-y-chat-luong-detail.component';
import { GopYChatLuongUpdateComponent } from './gop-y-chat-luong-update.component';
import { GopYChatLuongDeletePopupComponent } from './gop-y-chat-luong-delete-dialog.component';
import { IGopYChatLuong } from 'app/shared/model/gop-y-chat-luong.model';

@Injectable({ providedIn: 'root' })
export class GopYChatLuongResolve implements Resolve<IGopYChatLuong> {
  constructor(private service: GopYChatLuongService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGopYChatLuong> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((gopYChatLuong: HttpResponse<GopYChatLuong>) => gopYChatLuong.body));
    }
    return of(new GopYChatLuong());
  }
}

export const gopYChatLuongRoute: Routes = [
  {
    path: '',
    component: GopYChatLuongComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.gopYChatLuong.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GopYChatLuongDetailComponent,
    resolve: {
      gopYChatLuong: GopYChatLuongResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.gopYChatLuong.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GopYChatLuongUpdateComponent,
    resolve: {
      gopYChatLuong: GopYChatLuongResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.gopYChatLuong.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GopYChatLuongUpdateComponent,
    resolve: {
      gopYChatLuong: GopYChatLuongResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.gopYChatLuong.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const gopYChatLuongPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GopYChatLuongDeletePopupComponent,
    resolve: {
      gopYChatLuong: GopYChatLuongResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.gopYChatLuong.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
