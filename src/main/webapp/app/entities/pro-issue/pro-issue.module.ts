import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProIssueComponent } from './pro-issue.component';
import { ProIssueDetailComponent } from './pro-issue-detail.component';
import { ProIssueUpdateComponent } from './pro-issue-update.component';
import { ProIssueDeletePopupComponent, ProIssueDeleteDialogComponent } from './pro-issue-delete-dialog.component';
import { proIssueRoute, proIssuePopupRoute } from './pro-issue.route';

const ENTITY_STATES = [...proIssueRoute, ...proIssuePopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProIssueComponent,
    ProIssueDetailComponent,
    ProIssueUpdateComponent,
    ProIssueDeleteDialogComponent,
    ProIssueDeletePopupComponent
  ],
  entryComponents: [ProIssueDeleteDialogComponent]
})
export class CtProIssueModule {}
