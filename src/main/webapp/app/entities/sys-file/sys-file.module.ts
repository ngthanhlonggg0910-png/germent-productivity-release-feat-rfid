import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { SysFileComponent } from './sys-file.component';
import { SysFileDetailComponent } from './sys-file-detail.component';
import { SysFileUpdateComponent } from './sys-file-update.component';
import { SysFileDeletePopupComponent, SysFileDeleteDialogComponent } from './sys-file-delete-dialog.component';
import { sysFileRoute, sysFilePopupRoute } from './sys-file.route';

const ENTITY_STATES = [...sysFileRoute, ...sysFilePopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SysFileComponent,
    SysFileDetailComponent,
    SysFileUpdateComponent,
    SysFileDeleteDialogComponent,
    SysFileDeletePopupComponent
  ],
  entryComponents: [SysFileDeleteDialogComponent]
})
export class CtSysFileModule {}
