import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProNhomCongDoanComponent } from './pro-nhom-cong-doan.component';
import { ProNhomCongDoanDetailComponent } from './pro-nhom-cong-doan-detail.component';
import { ProNhomCongDoanUpdateComponent } from './pro-nhom-cong-doan-update.component';
import { ProNhomCongDoanDeletePopupComponent, ProNhomCongDoanDeleteDialogComponent } from './pro-nhom-cong-doan-delete-dialog.component';
import { proNhomCongDoanRoute, proNhomCongDoanPopupRoute } from './pro-nhom-cong-doan.route';

const ENTITY_STATES = [...proNhomCongDoanRoute, ...proNhomCongDoanPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProNhomCongDoanComponent,
    ProNhomCongDoanDetailComponent,
    ProNhomCongDoanUpdateComponent,
    ProNhomCongDoanDeleteDialogComponent,
    ProNhomCongDoanDeletePopupComponent
  ],
  entryComponents: [ProNhomCongDoanDeleteDialogComponent]
})
export class CtProNhomCongDoanModule {}
