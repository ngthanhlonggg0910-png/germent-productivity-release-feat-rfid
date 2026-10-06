import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { DoAmComponent } from './do-am.component';
import { DoAmDetailComponent } from './do-am-detail.component';
import { DoAmUpdateComponent } from './do-am-update.component';
import { DoAmDeletePopupComponent, DoAmDeleteDialogComponent } from './do-am-delete-dialog.component';
import { doAmRoute, doAmPopupRoute } from './do-am.route';

const ENTITY_STATES = [...doAmRoute, ...doAmPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [DoAmComponent, DoAmDetailComponent, DoAmUpdateComponent, DoAmDeleteDialogComponent, DoAmDeletePopupComponent],
  entryComponents: [DoAmDeleteDialogComponent]
})
export class CtDoAmModule {}
