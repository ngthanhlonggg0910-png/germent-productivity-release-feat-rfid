import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { KhacPhucLoiComponent } from './khac-phuc-loi.component';
import { KhacPhucLoiDetailComponent } from './khac-phuc-loi-detail.component';
import { KhacPhucLoiUpdateComponent } from './khac-phuc-loi-update.component';
import { KhacPhucLoiDeletePopupComponent, KhacPhucLoiDeleteDialogComponent } from './khac-phuc-loi-delete-dialog.component';
import { khacPhucLoiRoute, khacPhucLoiPopupRoute } from './khac-phuc-loi.route';

const ENTITY_STATES = [...khacPhucLoiRoute, ...khacPhucLoiPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    KhacPhucLoiComponent,
    KhacPhucLoiDetailComponent,
    KhacPhucLoiUpdateComponent,
    KhacPhucLoiDeleteDialogComponent,
    KhacPhucLoiDeletePopupComponent
  ],
  entryComponents: [KhacPhucLoiDeleteDialogComponent]
})
export class CtKhacPhucLoiModule {}
