import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProChuyenComponent } from './pro-chuyen.component';
import { ProChuyenDetailComponent } from './pro-chuyen-detail.component';
import { ProChuyenUpdateComponent } from './pro-chuyen-update.component';
import { ProChuyenDeletePopupComponent, ProChuyenDeleteDialogComponent } from './pro-chuyen-delete-dialog.component';
import { proChuyenRoute, proChuyenPopupRoute } from './pro-chuyen.route';

const ENTITY_STATES = [...proChuyenRoute, ...proChuyenPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProChuyenComponent,
    ProChuyenDetailComponent,
    ProChuyenUpdateComponent,
    ProChuyenDeleteDialogComponent,
    ProChuyenDeletePopupComponent
  ],
  entryComponents: [ProChuyenDeleteDialogComponent]
})
export class CtProChuyenModule {}
