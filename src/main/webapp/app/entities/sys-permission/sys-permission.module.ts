import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { SysPermissionComponent } from './sys-permission.component';
import { SysPermissionDetailComponent } from './sys-permission-detail.component';
import { SysPermissionUpdateComponent } from './sys-permission-update.component';
import { SysPermissionDeletePopupComponent, SysPermissionDeleteDialogComponent } from './sys-permission-delete-dialog.component';
import { sysPermissionRoute, sysPermissionPopupRoute } from './sys-permission.route';

const ENTITY_STATES = [...sysPermissionRoute, ...sysPermissionPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SysPermissionComponent,
    SysPermissionDetailComponent,
    SysPermissionUpdateComponent,
    SysPermissionDeleteDialogComponent,
    SysPermissionDeletePopupComponent
  ],
  entryComponents: [SysPermissionDeleteDialogComponent]
})
export class CtSysPermissionModule {}
