import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProCheckPointComponent } from './pro-check-point.component';
import { ProCheckPointDetailComponent } from './pro-check-point-detail.component';
import { ProCheckPointUpdateComponent } from './pro-check-point-update.component';
import { ProCheckPointDeletePopupComponent, ProCheckPointDeleteDialogComponent } from './pro-check-point-delete-dialog.component';
import { proCheckPointRoute, proCheckPointPopupRoute } from './pro-check-point.route';

const ENTITY_STATES = [...proCheckPointRoute, ...proCheckPointPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProCheckPointComponent,
    ProCheckPointDetailComponent,
    ProCheckPointUpdateComponent,
    ProCheckPointDeleteDialogComponent,
    ProCheckPointDeletePopupComponent
  ],
  entryComponents: [ProCheckPointDeleteDialogComponent]
})
export class CtProCheckPointModule {}
