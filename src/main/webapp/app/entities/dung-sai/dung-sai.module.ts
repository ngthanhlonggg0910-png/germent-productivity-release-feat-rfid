import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { DungSaiComponent } from './dung-sai.component';
import { DungSaiDetailComponent } from './dung-sai-detail.component';
import { DungSaiUpdateComponent } from './dung-sai-update.component';
import { DungSaiDeletePopupComponent, DungSaiDeleteDialogComponent } from './dung-sai-delete-dialog.component';
import { dungSaiRoute, dungSaiPopupRoute } from './dung-sai.route';

const ENTITY_STATES = [...dungSaiRoute, ...dungSaiPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DungSaiComponent,
    DungSaiDetailComponent,
    DungSaiUpdateComponent,
    DungSaiDeleteDialogComponent,
    DungSaiDeletePopupComponent
  ],
  entryComponents: [DungSaiDeleteDialogComponent]
})
export class CtDungSaiModule {}
