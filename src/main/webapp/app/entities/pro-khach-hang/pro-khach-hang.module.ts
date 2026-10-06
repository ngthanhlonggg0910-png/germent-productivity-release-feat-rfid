import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProKhachHangComponent } from './pro-khach-hang.component';
import { ProKhachHangDetailComponent } from './pro-khach-hang-detail.component';
import { ProKhachHangUpdateComponent } from './pro-khach-hang-update.component';
import { ProKhachHangDeletePopupComponent, ProKhachHangDeleteDialogComponent } from './pro-khach-hang-delete-dialog.component';
import { proKhachHangRoute, proKhachHangPopupRoute } from './pro-khach-hang.route';

const ENTITY_STATES = [...proKhachHangRoute, ...proKhachHangPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProKhachHangComponent,
    ProKhachHangDetailComponent,
    ProKhachHangUpdateComponent,
    ProKhachHangDeleteDialogComponent,
    ProKhachHangDeletePopupComponent
  ],
  entryComponents: [ProKhachHangDeleteDialogComponent]
})
export class CtProKhachHangModule {}
