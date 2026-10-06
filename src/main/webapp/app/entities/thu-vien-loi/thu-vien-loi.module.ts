import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ThuVienLoiComponent } from './thu-vien-loi.component';
import { ThuVienLoiDetailComponent } from './thu-vien-loi-detail.component';
import { ThuVienLoiUpdateComponent } from './thu-vien-loi-update.component';
import { ThuVienLoiDeletePopupComponent, ThuVienLoiDeleteDialogComponent } from './thu-vien-loi-delete-dialog.component';
import { thuVienLoiRoute, thuVienLoiPopupRoute } from './thu-vien-loi.route';

const ENTITY_STATES = [...thuVienLoiRoute, ...thuVienLoiPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ThuVienLoiComponent,
    ThuVienLoiDetailComponent,
    ThuVienLoiUpdateComponent,
    ThuVienLoiDeleteDialogComponent,
    ThuVienLoiDeletePopupComponent
  ],
  entryComponents: [ThuVienLoiDeleteDialogComponent]
})
export class CtThuVienLoiModule {}
