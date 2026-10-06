import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { GYCLQuyCachNPLComponent } from './gycl-quy-cach-npl.component';
import { GYCLQuyCachNPLDetailComponent } from './gycl-quy-cach-npl-detail.component';
import { GYCLQuyCachNPLUpdateComponent } from './gycl-quy-cach-npl-update.component';
import { GYCLQuyCachNPLDeletePopupComponent, GYCLQuyCachNPLDeleteDialogComponent } from './gycl-quy-cach-npl-delete-dialog.component';
import { gYCLQuyCachNPLRoute, gYCLQuyCachNPLPopupRoute } from './gycl-quy-cach-npl.route';

const ENTITY_STATES = [...gYCLQuyCachNPLRoute, ...gYCLQuyCachNPLPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    GYCLQuyCachNPLComponent,
    GYCLQuyCachNPLDetailComponent,
    GYCLQuyCachNPLUpdateComponent,
    GYCLQuyCachNPLDeleteDialogComponent,
    GYCLQuyCachNPLDeletePopupComponent
  ],
  entryComponents: [GYCLQuyCachNPLDeleteDialogComponent]
})
export class CtGYCLQuyCachNPLModule {}
