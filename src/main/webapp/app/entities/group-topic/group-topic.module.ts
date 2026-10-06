import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { GroupTopicComponent } from './group-topic.component';
import { GroupTopicDetailComponent } from './group-topic-detail.component';
import { GroupTopicUpdateComponent } from './group-topic-update.component';
import { GroupTopicDeletePopupComponent, GroupTopicDeleteDialogComponent } from './group-topic-delete-dialog.component';
import { groupTopicRoute, groupTopicPopupRoute } from './group-topic.route';

const ENTITY_STATES = [...groupTopicRoute, ...groupTopicPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    GroupTopicComponent,
    GroupTopicDetailComponent,
    GroupTopicUpdateComponent,
    GroupTopicDeleteDialogComponent,
    GroupTopicDeletePopupComponent
  ],
  entryComponents: [GroupTopicDeleteDialogComponent]
})
export class CtGroupTopicModule {}
