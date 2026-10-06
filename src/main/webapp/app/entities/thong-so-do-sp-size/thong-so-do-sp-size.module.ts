import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ThongSoDoSPSizeComponent } from './thong-so-do-sp-size.component';
import { ThongSoDoSPSizeDetailComponent } from './thong-so-do-sp-size-detail.component';
import { ThongSoDoSPSizeUpdateComponent } from './thong-so-do-sp-size-update.component';
import { ThongSoDoSPSizeDeletePopupComponent, ThongSoDoSPSizeDeleteDialogComponent } from './thong-so-do-sp-size-delete-dialog.component';
import { thongSoDoSPSizeRoute, thongSoDoSPSizePopupRoute } from './thong-so-do-sp-size.route';

const ENTITY_STATES = [...thongSoDoSPSizeRoute, ...thongSoDoSPSizePopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ThongSoDoSPSizeComponent,
    ThongSoDoSPSizeDetailComponent,
    ThongSoDoSPSizeUpdateComponent,
    ThongSoDoSPSizeDeleteDialogComponent,
    ThongSoDoSPSizeDeletePopupComponent
  ],
  entryComponents: [ThongSoDoSPSizeDeleteDialogComponent]
})
export class CtThongSoDoSPSizeModule {}
