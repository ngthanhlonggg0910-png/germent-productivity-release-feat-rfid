import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ThongSoDoSPComponent } from './thong-so-do-sp.component';
import { ThongSoDoSPDetailComponent } from './thong-so-do-sp-detail.component';
import { ThongSoDoSPUpdateComponent } from './thong-so-do-sp-update.component';
import { ThongSoDoSPDeletePopupComponent, ThongSoDoSPDeleteDialogComponent } from './thong-so-do-sp-delete-dialog.component';
import { thongSoDoSPRoute, thongSoDoSPPopupRoute } from './thong-so-do-sp.route';

const ENTITY_STATES = [...thongSoDoSPRoute, ...thongSoDoSPPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ThongSoDoSPComponent,
    ThongSoDoSPDetailComponent,
    ThongSoDoSPUpdateComponent,
    ThongSoDoSPDeleteDialogComponent,
    ThongSoDoSPDeletePopupComponent
  ],
  entryComponents: [ThongSoDoSPDeleteDialogComponent]
})
export class CtThongSoDoSPModule {}
