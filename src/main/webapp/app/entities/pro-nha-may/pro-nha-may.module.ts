import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProNhaMayComponent } from './pro-nha-may.component';
import { ProNhaMayDetailComponent } from './pro-nha-may-detail.component';
import { ProNhaMayUpdateComponent } from './pro-nha-may-update.component';
import { ProNhaMayDeletePopupComponent, ProNhaMayDeleteDialogComponent } from './pro-nha-may-delete-dialog.component';
import { proNhaMayRoute, proNhaMayPopupRoute } from './pro-nha-may.route';

const ENTITY_STATES = [...proNhaMayRoute, ...proNhaMayPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProNhaMayComponent,
    ProNhaMayDetailComponent,
    ProNhaMayUpdateComponent,
    ProNhaMayDeleteDialogComponent,
    ProNhaMayDeletePopupComponent
  ],
  entryComponents: [ProNhaMayDeleteDialogComponent]
})
export class CtProNhaMayModule {}
