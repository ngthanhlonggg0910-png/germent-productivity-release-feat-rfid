import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProCongTyComponent } from './pro-cong-ty.component';
import { ProCongTyDetailComponent } from './pro-cong-ty-detail.component';
import { ProCongTyUpdateComponent } from './pro-cong-ty-update.component';
import { ProCongTyDeletePopupComponent, ProCongTyDeleteDialogComponent } from './pro-cong-ty-delete-dialog.component';
import { proCongTyRoute, proCongTyPopupRoute } from './pro-cong-ty.route';

const ENTITY_STATES = [...proCongTyRoute, ...proCongTyPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProCongTyComponent,
    ProCongTyDetailComponent,
    ProCongTyUpdateComponent,
    ProCongTyDeleteDialogComponent,
    ProCongTyDeletePopupComponent
  ],
  entryComponents: [ProCongTyDeleteDialogComponent]
})
export class CtProCongTyModule {}
