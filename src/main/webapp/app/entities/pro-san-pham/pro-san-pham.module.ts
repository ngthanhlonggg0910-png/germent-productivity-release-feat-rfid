import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProSanPhamComponent } from './pro-san-pham.component';
import { ProSanPhamDetailComponent } from './pro-san-pham-detail.component';
import { ProSanPhamUpdateComponent } from './pro-san-pham-update.component';
import { ProSanPhamDeletePopupComponent, ProSanPhamDeleteDialogComponent } from './pro-san-pham-delete-dialog.component';
import { proSanPhamRoute, proSanPhamPopupRoute } from './pro-san-pham.route';

const ENTITY_STATES = [...proSanPhamRoute, ...proSanPhamPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProSanPhamComponent,
    ProSanPhamDetailComponent,
    ProSanPhamUpdateComponent,
    ProSanPhamDeleteDialogComponent,
    ProSanPhamDeletePopupComponent
  ],
  entryComponents: [ProSanPhamDeleteDialogComponent]
})
export class CtProSanPhamModule {}
