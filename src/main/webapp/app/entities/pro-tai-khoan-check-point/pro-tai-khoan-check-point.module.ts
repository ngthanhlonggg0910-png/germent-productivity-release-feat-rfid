import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProTaiKhoanCheckPointComponent } from './pro-tai-khoan-check-point.component';
import { ProTaiKhoanCheckPointDetailComponent } from './pro-tai-khoan-check-point-detail.component';
import { ProTaiKhoanCheckPointUpdateComponent } from './pro-tai-khoan-check-point-update.component';
import {
  ProTaiKhoanCheckPointDeletePopupComponent,
  ProTaiKhoanCheckPointDeleteDialogComponent
} from './pro-tai-khoan-check-point-delete-dialog.component';
import { proTaiKhoanCheckPointRoute, proTaiKhoanCheckPointPopupRoute } from './pro-tai-khoan-check-point.route';

const ENTITY_STATES = [...proTaiKhoanCheckPointRoute, ...proTaiKhoanCheckPointPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProTaiKhoanCheckPointComponent,
    ProTaiKhoanCheckPointDetailComponent,
    ProTaiKhoanCheckPointUpdateComponent,
    ProTaiKhoanCheckPointDeleteDialogComponent,
    ProTaiKhoanCheckPointDeletePopupComponent
  ],
  entryComponents: [ProTaiKhoanCheckPointDeleteDialogComponent]
})
export class CtProTaiKhoanCheckPointModule {}
