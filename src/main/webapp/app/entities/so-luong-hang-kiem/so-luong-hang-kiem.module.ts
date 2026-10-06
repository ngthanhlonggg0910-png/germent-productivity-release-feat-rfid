import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { SoLuongHangKiemComponent } from './so-luong-hang-kiem.component';
import { SoLuongHangKiemDetailComponent } from './so-luong-hang-kiem-detail.component';
import { SoLuongHangKiemUpdateComponent } from './so-luong-hang-kiem-update.component';
import { SoLuongHangKiemDeletePopupComponent, SoLuongHangKiemDeleteDialogComponent } from './so-luong-hang-kiem-delete-dialog.component';
import { soLuongHangKiemRoute, soLuongHangKiemPopupRoute } from './so-luong-hang-kiem.route';

const ENTITY_STATES = [...soLuongHangKiemRoute, ...soLuongHangKiemPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SoLuongHangKiemComponent,
    SoLuongHangKiemDetailComponent,
    SoLuongHangKiemUpdateComponent,
    SoLuongHangKiemDeleteDialogComponent,
    SoLuongHangKiemDeletePopupComponent
  ],
  entryComponents: [SoLuongHangKiemDeleteDialogComponent]
})
export class CtSoLuongHangKiemModule {}
