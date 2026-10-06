import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { TrackingComponent } from './tracking.component';
import { TrackingDetailComponent } from './tracking-detail.component';
import { TrackingUpdateComponent } from './tracking-update.component';
import { TrackingDeletePopupComponent, TrackingDeleteDialogComponent } from './tracking-delete-dialog.component';
import { trackingRoute, trackingPopupRoute } from './tracking.route';

const ENTITY_STATES = [...trackingRoute, ...trackingPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    TrackingComponent,
    TrackingDetailComponent,
    TrackingUpdateComponent,
    TrackingDeleteDialogComponent,
    TrackingDeletePopupComponent
  ],
  entryComponents: [TrackingDeleteDialogComponent]
})
export class CtTrackingModule {}
