import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { QuyCachNPLComponent } from './quy-cach-npl.component';
import { QuyCachNPLDetailComponent } from './quy-cach-npl-detail.component';
import { QuyCachNPLUpdateComponent } from './quy-cach-npl-update.component';
import { QuyCachNPLDeletePopupComponent, QuyCachNPLDeleteDialogComponent } from './quy-cach-npl-delete-dialog.component';
import { quyCachNPLRoute, quyCachNPLPopupRoute } from './quy-cach-npl.route';

const ENTITY_STATES = [...quyCachNPLRoute, ...quyCachNPLPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    QuyCachNPLComponent,
    QuyCachNPLDetailComponent,
    QuyCachNPLUpdateComponent,
    QuyCachNPLDeleteDialogComponent,
    QuyCachNPLDeletePopupComponent
  ],
  entryComponents: [QuyCachNPLDeleteDialogComponent]
})
export class CtQuyCachNPLModule {}
