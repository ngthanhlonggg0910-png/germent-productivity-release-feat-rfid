import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { SysRoleHasFunctionComponent } from './sys-role-has-function.component';
import { SysRoleHasFunctionDetailComponent } from './sys-role-has-function-detail.component';
import { SysRoleHasFunctionUpdateComponent } from './sys-role-has-function-update.component';
import {
  SysRoleHasFunctionDeletePopupComponent,
  SysRoleHasFunctionDeleteDialogComponent
} from './sys-role-has-function-delete-dialog.component';
import { sysRoleHasFunctionRoute, sysRoleHasFunctionPopupRoute } from './sys-role-has-function.route';

const ENTITY_STATES = [...sysRoleHasFunctionRoute, ...sysRoleHasFunctionPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    SysRoleHasFunctionComponent,
    SysRoleHasFunctionDetailComponent,
    SysRoleHasFunctionUpdateComponent,
    SysRoleHasFunctionDeleteDialogComponent,
    SysRoleHasFunctionDeletePopupComponent
  ],
  entryComponents: [SysRoleHasFunctionDeleteDialogComponent]
})
export class CtSysRoleHasFunctionModule {}
