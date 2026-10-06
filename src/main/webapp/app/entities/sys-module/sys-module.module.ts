import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { SysModuleComponent } from './sys-module.component';
import { SysModuleDetailComponent } from './sys-module-detail.component';
import { SysModuleUpdateComponent } from './sys-module-update.component';
import { SysModuleDeletePopupComponent, SysModuleDeleteDialogComponent } from './sys-module-delete-dialog.component';
import { sysModuleRoute, sysModulePopupRoute } from './sys-module.route';

const ENTITY_STATES = [...sysModuleRoute, ...sysModulePopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SysModuleComponent,
    SysModuleDetailComponent,
    SysModuleUpdateComponent,
    SysModuleDeleteDialogComponent,
    SysModuleDeletePopupComponent
  ],
  entryComponents: [SysModuleDeleteDialogComponent]
})
export class CtSysModuleModule {}
