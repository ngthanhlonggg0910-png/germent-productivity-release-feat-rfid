import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProRefinalComponent } from './pro-refinal.component';
import { ProRefinalDetailComponent } from './pro-refinal-detail.component';
import { ProRefinalUpdateComponent } from './pro-refinal-update.component';
import { ProRefinalDeletePopupComponent, ProRefinalDeleteDialogComponent } from './pro-refinal-delete-dialog.component';
import { proRefinalRoute, proRefinalPopupRoute } from './pro-refinal.route';

const ENTITY_STATES = [...proRefinalRoute, ...proRefinalPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProRefinalComponent,
    ProRefinalDetailComponent,
    ProRefinalUpdateComponent,
    ProRefinalDeleteDialogComponent,
    ProRefinalDeletePopupComponent
  ],
  entryComponents: [ProRefinalDeleteDialogComponent]
})
export class CtProRefinalModule {}
