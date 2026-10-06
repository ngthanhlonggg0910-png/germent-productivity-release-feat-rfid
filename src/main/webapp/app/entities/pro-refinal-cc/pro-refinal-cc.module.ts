import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProRefinalCCComponent } from './pro-refinal-cc.component';
import { ProRefinalCCDetailComponent } from './pro-refinal-cc-detail.component';
import { ProRefinalCCUpdateComponent } from './pro-refinal-cc-update.component';
import { ProRefinalCCDeletePopupComponent, ProRefinalCCDeleteDialogComponent } from './pro-refinal-cc-delete-dialog.component';
import { proRefinalCCRoute, proRefinalCCPopupRoute } from './pro-refinal-cc.route';

const ENTITY_STATES = [...proRefinalCCRoute, ...proRefinalCCPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProRefinalCCComponent,
    ProRefinalCCDetailComponent,
    ProRefinalCCUpdateComponent,
    ProRefinalCCDeleteDialogComponent,
    ProRefinalCCDeletePopupComponent
  ],
  entryComponents: [ProRefinalCCDeleteDialogComponent]
})
export class CtProRefinalCCModule {}
