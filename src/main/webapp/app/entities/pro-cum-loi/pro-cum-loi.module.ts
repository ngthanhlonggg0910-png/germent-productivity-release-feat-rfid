import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProCumLoiComponent } from './pro-cum-loi.component';
import { ProCumLoiDetailComponent } from './pro-cum-loi-detail.component';
import { ProCumLoiUpdateComponent } from './pro-cum-loi-update.component';
import { ProCumLoiDeletePopupComponent, ProCumLoiDeleteDialogComponent } from './pro-cum-loi-delete-dialog.component';
import { proCumLoiRoute, proCumLoiPopupRoute } from './pro-cum-loi.route';

const ENTITY_STATES = [...proCumLoiRoute, ...proCumLoiPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProCumLoiComponent,
    ProCumLoiDetailComponent,
    ProCumLoiUpdateComponent,
    ProCumLoiDeleteDialogComponent,
    ProCumLoiDeletePopupComponent
  ],
  entryComponents: [ProCumLoiDeleteDialogComponent]
})
export class CtProCumLoiModule {}
