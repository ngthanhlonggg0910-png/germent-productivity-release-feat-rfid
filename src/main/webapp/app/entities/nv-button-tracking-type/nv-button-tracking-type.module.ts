import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { NVButtonTrackingTypeComponent } from './nv-button-tracking-type.component';
import { NVButtonTrackingTypeDetailComponent } from './nv-button-tracking-type-detail.component';
import { NVButtonTrackingTypeUpdateComponent } from './nv-button-tracking-type-update.component';
import {
  NVButtonTrackingTypeDeletePopupComponent,
  NVButtonTrackingTypeDeleteDialogComponent
} from './nv-button-tracking-type-delete-dialog.component';
import { nVButtonTrackingTypeRoute, nVButtonTrackingTypePopupRoute } from './nv-button-tracking-type.route';

const ENTITY_STATES = [...nVButtonTrackingTypeRoute, ...nVButtonTrackingTypePopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    NVButtonTrackingTypeComponent,
    NVButtonTrackingTypeDetailComponent,
    NVButtonTrackingTypeUpdateComponent,
    NVButtonTrackingTypeDeleteDialogComponent,
    NVButtonTrackingTypeDeletePopupComponent
  ],
  entryComponents: [NVButtonTrackingTypeDeleteDialogComponent]
})
export class CtNVButtonTrackingTypeModule {}
