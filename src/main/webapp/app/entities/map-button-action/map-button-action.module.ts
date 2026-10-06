import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { MapButtonActionComponent } from './map-button-action.component';
import { MapButtonActionDetailComponent } from './map-button-action-detail.component';
import { MapButtonActionUpdateComponent } from './map-button-action-update.component';
import { MapButtonActionDeletePopupComponent, MapButtonActionDeleteDialogComponent } from './map-button-action-delete-dialog.component';
import { mapButtonActionRoute, mapButtonActionPopupRoute } from './map-button-action.route';

const ENTITY_STATES = [...mapButtonActionRoute, ...mapButtonActionPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MapButtonActionComponent,
    MapButtonActionDetailComponent,
    MapButtonActionUpdateComponent,
    MapButtonActionDeleteDialogComponent,
    MapButtonActionDeletePopupComponent
  ],
  entryComponents: [MapButtonActionDeleteDialogComponent]
})
export class CtMapButtonActionModule {}
