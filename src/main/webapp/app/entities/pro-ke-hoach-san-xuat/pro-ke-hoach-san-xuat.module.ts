import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProKeHoachSanXuatComponent } from './pro-ke-hoach-san-xuat.component';
import { ProKeHoachSanXuatDetailComponent } from './pro-ke-hoach-san-xuat-detail.component';
import { ProKeHoachSanXuatUpdateComponent } from './pro-ke-hoach-san-xuat-update.component';
import {
  ProKeHoachSanXuatDeletePopupComponent,
  ProKeHoachSanXuatDeleteDialogComponent
} from './pro-ke-hoach-san-xuat-delete-dialog.component';
import { proKeHoachSanXuatRoute, proKeHoachSanXuatPopupRoute } from './pro-ke-hoach-san-xuat.route';

const ENTITY_STATES = [...proKeHoachSanXuatRoute, ...proKeHoachSanXuatPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProKeHoachSanXuatComponent,
    ProKeHoachSanXuatDetailComponent,
    ProKeHoachSanXuatUpdateComponent,
    ProKeHoachSanXuatDeleteDialogComponent,
    ProKeHoachSanXuatDeletePopupComponent
  ],
  entryComponents: [ProKeHoachSanXuatDeleteDialogComponent]
})
export class CtProKeHoachSanXuatModule {}
