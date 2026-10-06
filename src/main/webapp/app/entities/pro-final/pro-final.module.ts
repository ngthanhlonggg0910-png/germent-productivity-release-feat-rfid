import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProFinalComponent } from './pro-final.component';
import { ProFinalDetailComponent } from './pro-final-detail.component';
import { ProFinalUpdateComponent } from './pro-final-update.component';
import { ProFinalDeletePopupComponent, ProFinalDeleteDialogComponent } from './pro-final-delete-dialog.component';
import { proFinalRoute, proFinalPopupRoute } from './pro-final.route';

const ENTITY_STATES = [...proFinalRoute, ...proFinalPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProFinalComponent,
    ProFinalDetailComponent,
    ProFinalUpdateComponent,
    ProFinalDeleteDialogComponent,
    ProFinalDeletePopupComponent
  ],
  entryComponents: [ProFinalDeleteDialogComponent]
})
export class CtProFinalModule {}
