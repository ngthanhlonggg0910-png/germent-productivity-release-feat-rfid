import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { GroupTopic } from 'app/shared/model/group-topic.model';
import { GroupTopicService } from './group-topic.service';
import { GroupTopicComponent } from './group-topic.component';
import { GroupTopicDetailComponent } from './group-topic-detail.component';
import { GroupTopicUpdateComponent } from './group-topic-update.component';
import { GroupTopicDeletePopupComponent } from './group-topic-delete-dialog.component';
import { IGroupTopic } from 'app/shared/model/group-topic.model';

@Injectable({ providedIn: 'root' })
export class GroupTopicResolve implements Resolve<IGroupTopic> {
  constructor(private service: GroupTopicService) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGroupTopic> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(map((groupTopic: HttpResponse<GroupTopic>) => groupTopic.body));
    }
    return of(new GroupTopic());
  }
}

export const groupTopicRoute: Routes = [
  {
    path: '',
    component: GroupTopicComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'ctApp.groupTopic.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GroupTopicDetailComponent,
    resolve: {
      groupTopic: GroupTopicResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.groupTopic.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GroupTopicUpdateComponent,
    resolve: {
      groupTopic: GroupTopicResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.groupTopic.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GroupTopicUpdateComponent,
    resolve: {
      groupTopic: GroupTopicResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.groupTopic.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const groupTopicPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GroupTopicDeletePopupComponent,
    resolve: {
      groupTopic: GroupTopicResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'ctApp.groupTopic.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
