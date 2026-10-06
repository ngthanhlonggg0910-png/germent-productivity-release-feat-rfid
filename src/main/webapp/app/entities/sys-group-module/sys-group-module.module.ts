import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { SysGroupModuleComponent } from './sys-group-module.component';
import { SysGroupModuleDetailComponent } from './sys-group-module-detail.component';
import { SysGroupModuleUpdateComponent } from './sys-group-module-update.component';
import { SysGroupModuleDeletePopupComponent, SysGroupModuleDeleteDialogComponent } from './sys-group-module-delete-dialog.component';
import { sysGroupModuleRoute, sysGroupModulePopupRoute } from './sys-group-module.route';

const ENTITY_STATES = [...sysGroupModuleRoute, ...sysGroupModulePopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SysGroupModuleComponent,
    SysGroupModuleDetailComponent,
    SysGroupModuleUpdateComponent,
    SysGroupModuleDeleteDialogComponent,
    SysGroupModuleDeletePopupComponent
  ],
  entryComponents: [SysGroupModuleDeleteDialogComponent]
})
export class CtSysGroupModuleModule {}
