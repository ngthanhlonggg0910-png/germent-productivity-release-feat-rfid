import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ButtonTrackingComponent } from './button-tracking.component';
import { ButtonTrackingDetailComponent } from './button-tracking-detail.component';
import { ButtonTrackingUpdateComponent } from './button-tracking-update.component';
import { ButtonTrackingDeletePopupComponent, ButtonTrackingDeleteDialogComponent } from './button-tracking-delete-dialog.component';
import { buttonTrackingRoute, buttonTrackingPopupRoute } from './button-tracking.route';

const ENTITY_STATES = [...buttonTrackingRoute, ...buttonTrackingPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ButtonTrackingComponent,
    ButtonTrackingDetailComponent,
    ButtonTrackingUpdateComponent,
    ButtonTrackingDeleteDialogComponent,
    ButtonTrackingDeletePopupComponent
  ],
  entryComponents: [ButtonTrackingDeleteDialogComponent]
})
export class CtButtonTrackingModule {}
