import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProCongDoanComponent } from './pro-cong-doan.component';
import { ProCongDoanDetailComponent } from './pro-cong-doan-detail.component';
import { ProCongDoanUpdateComponent } from './pro-cong-doan-update.component';
import { ProCongDoanDeletePopupComponent, ProCongDoanDeleteDialogComponent } from './pro-cong-doan-delete-dialog.component';
import { proCongDoanRoute, proCongDoanPopupRoute } from './pro-cong-doan.route';

const ENTITY_STATES = [...proCongDoanRoute, ...proCongDoanPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProCongDoanComponent,
    ProCongDoanDetailComponent,
    ProCongDoanUpdateComponent,
    ProCongDoanDeleteDialogComponent,
    ProCongDoanDeletePopupComponent
  ],
  entryComponents: [ProCongDoanDeleteDialogComponent]
})
export class CtProCongDoanModule {}
