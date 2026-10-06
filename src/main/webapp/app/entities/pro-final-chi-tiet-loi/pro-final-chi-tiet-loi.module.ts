import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProFinalChiTietLoiComponent } from './pro-final-chi-tiet-loi.component';
import { ProFinalChiTietLoiDetailComponent } from './pro-final-chi-tiet-loi-detail.component';
import { ProFinalChiTietLoiUpdateComponent } from './pro-final-chi-tiet-loi-update.component';
import {
  ProFinalChiTietLoiDeletePopupComponent,
  ProFinalChiTietLoiDeleteDialogComponent
} from './pro-final-chi-tiet-loi-delete-dialog.component';
import { proFinalChiTietLoiRoute, proFinalChiTietLoiPopupRoute } from './pro-final-chi-tiet-loi.route';

const ENTITY_STATES = [...proFinalChiTietLoiRoute, ...proFinalChiTietLoiPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProFinalChiTietLoiComponent,
    ProFinalChiTietLoiDetailComponent,
    ProFinalChiTietLoiUpdateComponent,
    ProFinalChiTietLoiDeleteDialogComponent,
    ProFinalChiTietLoiDeletePopupComponent
  ],
  entryComponents: [ProFinalChiTietLoiDeleteDialogComponent]
})
export class CtProFinalChiTietLoiModule {}
