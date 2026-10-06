import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProIssueLoiComponent } from './pro-issue-loi.component';
import { ProIssueLoiDetailComponent } from './pro-issue-loi-detail.component';
import { ProIssueLoiUpdateComponent } from './pro-issue-loi-update.component';
import { ProIssueLoiDeletePopupComponent, ProIssueLoiDeleteDialogComponent } from './pro-issue-loi-delete-dialog.component';
import { proIssueLoiRoute, proIssueLoiPopupRoute } from './pro-issue-loi.route';

const ENTITY_STATES = [...proIssueLoiRoute, ...proIssueLoiPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProIssueLoiComponent,
    ProIssueLoiDetailComponent,
    ProIssueLoiUpdateComponent,
    ProIssueLoiDeleteDialogComponent,
    ProIssueLoiDeletePopupComponent
  ],
  entryComponents: [ProIssueLoiDeleteDialogComponent]
})
export class CtProIssueLoiModule {}
