import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { MapControlButtonComponent } from './map-control-button.component';
import { MapControlButtonDetailComponent } from './map-control-button-detail.component';
import { MapControlButtonUpdateComponent } from './map-control-button-update.component';
import { MapControlButtonDeletePopupComponent, MapControlButtonDeleteDialogComponent } from './map-control-button-delete-dialog.component';
import { mapControlButtonRoute, mapControlButtonPopupRoute } from './map-control-button.route';

const ENTITY_STATES = [...mapControlButtonRoute, ...mapControlButtonPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MapControlButtonComponent,
    MapControlButtonDetailComponent,
    MapControlButtonUpdateComponent,
    MapControlButtonDeleteDialogComponent,
    MapControlButtonDeletePopupComponent
  ],
  entryComponents: [MapControlButtonDeleteDialogComponent]
})
export class CtMapControlButtonModule {}
