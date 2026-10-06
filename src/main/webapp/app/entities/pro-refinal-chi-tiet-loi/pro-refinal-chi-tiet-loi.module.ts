import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProRefinalChiTietLoiComponent } from './pro-refinal-chi-tiet-loi.component';
import { ProRefinalChiTietLoiDetailComponent } from './pro-refinal-chi-tiet-loi-detail.component';
import { ProRefinalChiTietLoiUpdateComponent } from './pro-refinal-chi-tiet-loi-update.component';
import {
  ProRefinalChiTietLoiDeletePopupComponent,
  ProRefinalChiTietLoiDeleteDialogComponent
} from './pro-refinal-chi-tiet-loi-delete-dialog.component';
import { proRefinalChiTietLoiRoute, proRefinalChiTietLoiPopupRoute } from './pro-refinal-chi-tiet-loi.route';

const ENTITY_STATES = [...proRefinalChiTietLoiRoute, ...proRefinalChiTietLoiPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProRefinalChiTietLoiComponent,
    ProRefinalChiTietLoiDetailComponent,
    ProRefinalChiTietLoiUpdateComponent,
    ProRefinalChiTietLoiDeleteDialogComponent,
    ProRefinalChiTietLoiDeletePopupComponent
  ],
  entryComponents: [ProRefinalChiTietLoiDeleteDialogComponent]
})
export class CtProRefinalChiTietLoiModule {}
