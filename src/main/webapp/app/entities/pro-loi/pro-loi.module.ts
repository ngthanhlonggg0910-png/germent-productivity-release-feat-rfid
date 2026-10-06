import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProLoiComponent } from './pro-loi.component';
import { ProLoiDetailComponent } from './pro-loi-detail.component';
import { ProLoiUpdateComponent } from './pro-loi-update.component';
import { ProLoiDeletePopupComponent, ProLoiDeleteDialogComponent } from './pro-loi-delete-dialog.component';
import { proLoiRoute, proLoiPopupRoute } from './pro-loi.route';

const ENTITY_STATES = [...proLoiRoute, ...proLoiPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [ProLoiComponent, ProLoiDetailComponent, ProLoiUpdateComponent, ProLoiDeleteDialogComponent, ProLoiDeletePopupComponent],
  entryComponents: [ProLoiDeleteDialogComponent]
})
export class CtProLoiModule {}
