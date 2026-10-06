import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { DoKimMdComponent } from './do-kim-md.component';
import { DoKimMdDetailComponent } from './do-kim-md-detail.component';
import { DoKimMdUpdateComponent } from './do-kim-md-update.component';
import { DoKimMdDeletePopupComponent, DoKimMdDeleteDialogComponent } from './do-kim-md-delete-dialog.component';
import { doKimMdRoute, doKimMdPopupRoute } from './do-kim-md.route';

const ENTITY_STATES = [...doKimMdRoute, ...doKimMdPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    DoKimMdComponent,
    DoKimMdDetailComponent,
    DoKimMdUpdateComponent,
    DoKimMdDeleteDialogComponent,
    DoKimMdDeletePopupComponent
  ],
  entryComponents: [DoKimMdDeleteDialogComponent]
})
export class CtDoKimMdModule {}
