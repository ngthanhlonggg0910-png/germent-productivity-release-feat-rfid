import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { NVButtonComponent } from './nv-button.component';
import { NVButtonDetailComponent } from './nv-button-detail.component';
import { NVButtonUpdateComponent } from './nv-button-update.component';
import { NVButtonDeletePopupComponent, NVButtonDeleteDialogComponent } from './nv-button-delete-dialog.component';
import { nVButtonRoute, nVButtonPopupRoute } from './nv-button.route';

const ENTITY_STATES = [...nVButtonRoute, ...nVButtonPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    NVButtonComponent,
    NVButtonDetailComponent,
    NVButtonUpdateComponent,
    NVButtonDeleteDialogComponent,
    NVButtonDeletePopupComponent
  ],
  entryComponents: [NVButtonDeleteDialogComponent]
})
export class CtNVButtonModule {}
