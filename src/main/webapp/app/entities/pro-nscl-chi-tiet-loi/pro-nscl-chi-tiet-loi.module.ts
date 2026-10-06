import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProNSCLChiTietLoiComponent } from './pro-nscl-chi-tiet-loi.component';
import { ProNSCLChiTietLoiDetailComponent } from './pro-nscl-chi-tiet-loi-detail.component';
import { ProNSCLChiTietLoiUpdateComponent } from './pro-nscl-chi-tiet-loi-update.component';
import {
  ProNSCLChiTietLoiDeletePopupComponent,
  ProNSCLChiTietLoiDeleteDialogComponent
} from './pro-nscl-chi-tiet-loi-delete-dialog.component';
import { proNSCLChiTietLoiRoute, proNSCLChiTietLoiPopupRoute } from './pro-nscl-chi-tiet-loi.route';

const ENTITY_STATES = [...proNSCLChiTietLoiRoute, ...proNSCLChiTietLoiPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProNSCLChiTietLoiComponent,
    ProNSCLChiTietLoiDetailComponent,
    ProNSCLChiTietLoiUpdateComponent,
    ProNSCLChiTietLoiDeleteDialogComponent,
    ProNSCLChiTietLoiDeletePopupComponent
  ],
  entryComponents: [ProNSCLChiTietLoiDeleteDialogComponent]
})
export class CtProNSCLChiTietLoiModule {}
