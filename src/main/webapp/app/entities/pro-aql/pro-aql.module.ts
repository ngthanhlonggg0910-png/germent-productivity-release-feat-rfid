import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProAqlComponent } from './pro-aql.component';
import { ProAqlDetailComponent } from './pro-aql-detail.component';
import { ProAqlUpdateComponent } from './pro-aql-update.component';
import { ProAqlDeletePopupComponent, ProAqlDeleteDialogComponent } from './pro-aql-delete-dialog.component';
import { proAqlRoute, proAqlPopupRoute } from './pro-aql.route';

const ENTITY_STATES = [...proAqlRoute, ...proAqlPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [ProAqlComponent, ProAqlDetailComponent, ProAqlUpdateComponent, ProAqlDeleteDialogComponent, ProAqlDeletePopupComponent],
  entryComponents: [ProAqlDeleteDialogComponent]
})
export class CtProAqlModule {}
