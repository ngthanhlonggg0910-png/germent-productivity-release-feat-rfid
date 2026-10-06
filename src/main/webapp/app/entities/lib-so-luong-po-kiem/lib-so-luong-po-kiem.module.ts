import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { LibSoLuongPoKiemComponent } from './lib-so-luong-po-kiem.component';
import { LibSoLuongPoKiemDetailComponent } from './lib-so-luong-po-kiem-detail.component';
import { LibSoLuongPoKiemUpdateComponent } from './lib-so-luong-po-kiem-update.component';
import {
  LibSoLuongPoKiemDeletePopupComponent,
  LibSoLuongPoKiemDeleteDialogComponent
} from './lib-so-luong-po-kiem-delete-dialog.component';
import { libSoLuongPoKiemRoute, libSoLuongPoKiemPopupRoute } from './lib-so-luong-po-kiem.route';

const ENTITY_STATES = [...libSoLuongPoKiemRoute, ...libSoLuongPoKiemPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    LibSoLuongPoKiemComponent,
    LibSoLuongPoKiemDetailComponent,
    LibSoLuongPoKiemUpdateComponent,
    LibSoLuongPoKiemDeleteDialogComponent,
    LibSoLuongPoKiemDeletePopupComponent
  ],
  entryComponents: [LibSoLuongPoKiemDeleteDialogComponent]
})
export class CtLibSoLuongPoKiemModule {}
