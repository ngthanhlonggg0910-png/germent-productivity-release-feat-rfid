import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ThongSoThucThiComponent } from './thong-so-thuc-thi.component';
import { ThongSoThucThiDetailComponent } from './thong-so-thuc-thi-detail.component';
import { ThongSoThucThiUpdateComponent } from './thong-so-thuc-thi-update.component';
import { ThongSoThucThiDeletePopupComponent, ThongSoThucThiDeleteDialogComponent } from './thong-so-thuc-thi-delete-dialog.component';
import { thongSoThucThiRoute, thongSoThucThiPopupRoute } from './thong-so-thuc-thi.route';

const ENTITY_STATES = [...thongSoThucThiRoute, ...thongSoThucThiPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ThongSoThucThiComponent,
    ThongSoThucThiDetailComponent,
    ThongSoThucThiUpdateComponent,
    ThongSoThucThiDeleteDialogComponent,
    ThongSoThucThiDeletePopupComponent
  ],
  entryComponents: [ThongSoThucThiDeleteDialogComponent]
})
export class CtThongSoThucThiModule {}
