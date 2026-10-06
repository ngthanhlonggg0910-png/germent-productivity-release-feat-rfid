import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProChungLoaiSanPhamComponent } from './pro-chung-loai-san-pham.component';
import { ProChungLoaiSanPhamDetailComponent } from './pro-chung-loai-san-pham-detail.component';
import { ProChungLoaiSanPhamUpdateComponent } from './pro-chung-loai-san-pham-update.component';
import {
  ProChungLoaiSanPhamDeletePopupComponent,
  ProChungLoaiSanPhamDeleteDialogComponent
} from './pro-chung-loai-san-pham-delete-dialog.component';
import { proChungLoaiSanPhamRoute, proChungLoaiSanPhamPopupRoute } from './pro-chung-loai-san-pham.route';

const ENTITY_STATES = [...proChungLoaiSanPhamRoute, ...proChungLoaiSanPhamPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProChungLoaiSanPhamComponent,
    ProChungLoaiSanPhamDetailComponent,
    ProChungLoaiSanPhamUpdateComponent,
    ProChungLoaiSanPhamDeleteDialogComponent,
    ProChungLoaiSanPhamDeletePopupComponent
  ],
  entryComponents: [ProChungLoaiSanPhamDeleteDialogComponent]
})
export class CtProChungLoaiSanPhamModule {}
