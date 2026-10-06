import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProRefinalCCChiTietLoiComponent } from './pro-refinal-cc-chi-tiet-loi.component';
import { ProRefinalCCChiTietLoiDetailComponent } from './pro-refinal-cc-chi-tiet-loi-detail.component';
import { ProRefinalCCChiTietLoiUpdateComponent } from './pro-refinal-cc-chi-tiet-loi-update.component';
import {
  ProRefinalCCChiTietLoiDeletePopupComponent,
  ProRefinalCCChiTietLoiDeleteDialogComponent
} from './pro-refinal-cc-chi-tiet-loi-delete-dialog.component';
import { proRefinalCCChiTietLoiRoute, proRefinalCCChiTietLoiPopupRoute } from './pro-refinal-cc-chi-tiet-loi.route';

const ENTITY_STATES = [...proRefinalCCChiTietLoiRoute, ...proRefinalCCChiTietLoiPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProRefinalCCChiTietLoiComponent,
    ProRefinalCCChiTietLoiDetailComponent,
    ProRefinalCCChiTietLoiUpdateComponent,
    ProRefinalCCChiTietLoiDeleteDialogComponent,
    ProRefinalCCChiTietLoiDeletePopupComponent
  ],
  entryComponents: [ProRefinalCCChiTietLoiDeleteDialogComponent]
})
export class CtProRefinalCCChiTietLoiModule {}
