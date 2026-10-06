import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProLoaiChuyenComponent } from './pro-loai-chuyen.component';
import { ProLoaiChuyenDetailComponent } from './pro-loai-chuyen-detail.component';
import { ProLoaiChuyenUpdateComponent } from './pro-loai-chuyen-update.component';
import { ProLoaiChuyenDeletePopupComponent, ProLoaiChuyenDeleteDialogComponent } from './pro-loai-chuyen-delete-dialog.component';
import { proLoaiChuyenRoute, proLoaiChuyenPopupRoute } from './pro-loai-chuyen.route';

const ENTITY_STATES = [...proLoaiChuyenRoute, ...proLoaiChuyenPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProLoaiChuyenComponent,
    ProLoaiChuyenDetailComponent,
    ProLoaiChuyenUpdateComponent,
    ProLoaiChuyenDeleteDialogComponent,
    ProLoaiChuyenDeletePopupComponent
  ],
  entryComponents: [ProLoaiChuyenDeleteDialogComponent]
})
export class CtProLoaiChuyenModule {}
