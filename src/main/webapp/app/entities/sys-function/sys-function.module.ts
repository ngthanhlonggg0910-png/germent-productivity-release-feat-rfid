import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { SysFunctionComponent } from './sys-function.component';
import { SysFunctionDetailComponent } from './sys-function-detail.component';
import { SysFunctionUpdateComponent } from './sys-function-update.component';
import { SysFunctionDeletePopupComponent, SysFunctionDeleteDialogComponent } from './sys-function-delete-dialog.component';
import { sysFunctionRoute, sysFunctionPopupRoute } from './sys-function.route';

const ENTITY_STATES = [...sysFunctionRoute, ...sysFunctionPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SysFunctionComponent,
    SysFunctionDetailComponent,
    SysFunctionUpdateComponent,
    SysFunctionDeleteDialogComponent,
    SysFunctionDeletePopupComponent
  ],
  entryComponents: [SysFunctionDeleteDialogComponent]
})
export class CtSysFunctionModule {}
