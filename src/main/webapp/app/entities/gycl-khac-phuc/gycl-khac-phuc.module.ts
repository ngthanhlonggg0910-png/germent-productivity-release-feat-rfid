import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { GYCLKhacPhucComponent } from './gycl-khac-phuc.component';
import { GYCLKhacPhucDetailComponent } from './gycl-khac-phuc-detail.component';
import { GYCLKhacPhucUpdateComponent } from './gycl-khac-phuc-update.component';
import { GYCLKhacPhucDeletePopupComponent, GYCLKhacPhucDeleteDialogComponent } from './gycl-khac-phuc-delete-dialog.component';
import { gYCLKhacPhucRoute, gYCLKhacPhucPopupRoute } from './gycl-khac-phuc.route';

const ENTITY_STATES = [...gYCLKhacPhucRoute, ...gYCLKhacPhucPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    GYCLKhacPhucComponent,
    GYCLKhacPhucDetailComponent,
    GYCLKhacPhucUpdateComponent,
    GYCLKhacPhucDeleteDialogComponent,
    GYCLKhacPhucDeletePopupComponent
  ],
  entryComponents: [GYCLKhacPhucDeleteDialogComponent]
})
export class CtGYCLKhacPhucModule {}
