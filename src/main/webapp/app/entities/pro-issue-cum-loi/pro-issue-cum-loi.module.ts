import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProIssueCumLoiComponent } from './pro-issue-cum-loi.component';
import { ProIssueCumLoiDetailComponent } from './pro-issue-cum-loi-detail.component';
import { ProIssueCumLoiUpdateComponent } from './pro-issue-cum-loi-update.component';
import { ProIssueCumLoiDeletePopupComponent, ProIssueCumLoiDeleteDialogComponent } from './pro-issue-cum-loi-delete-dialog.component';
import { proIssueCumLoiRoute, proIssueCumLoiPopupRoute } from './pro-issue-cum-loi.route';

const ENTITY_STATES = [...proIssueCumLoiRoute, ...proIssueCumLoiPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProIssueCumLoiComponent,
    ProIssueCumLoiDetailComponent,
    ProIssueCumLoiUpdateComponent,
    ProIssueCumLoiDeleteDialogComponent,
    ProIssueCumLoiDeletePopupComponent
  ],
  entryComponents: [ProIssueCumLoiDeleteDialogComponent]
})
export class CtProIssueCumLoiModule {}
