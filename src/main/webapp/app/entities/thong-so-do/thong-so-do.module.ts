import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ThongSoDoComponent } from './thong-so-do.component';
import { ThongSoDoDetailComponent } from './thong-so-do-detail.component';
import { ThongSoDoUpdateComponent } from './thong-so-do-update.component';
import { ThongSoDoDeletePopupComponent, ThongSoDoDeleteDialogComponent } from './thong-so-do-delete-dialog.component';
import { thongSoDoRoute, thongSoDoPopupRoute } from './thong-so-do.route';

const ENTITY_STATES = [...thongSoDoRoute, ...thongSoDoPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ThongSoDoComponent,
    ThongSoDoDetailComponent,
    ThongSoDoUpdateComponent,
    ThongSoDoDeleteDialogComponent,
    ThongSoDoDeletePopupComponent
  ],
  entryComponents: [ThongSoDoDeleteDialogComponent]
})
export class CtThongSoDoModule {}
