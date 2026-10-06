import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProTenMuaComponent } from './pro-ten-mua.component';
import { ProTenMuaDetailComponent } from './pro-ten-mua-detail.component';
import { ProTenMuaUpdateComponent } from './pro-ten-mua-update.component';
import { ProTenMuaDeletePopupComponent, ProTenMuaDeleteDialogComponent } from './pro-ten-mua-delete-dialog.component';
import { proTenMuaRoute, proTenMuaPopupRoute } from './pro-ten-mua.route';

const ENTITY_STATES = [...proTenMuaRoute, ...proTenMuaPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProTenMuaComponent,
    ProTenMuaDetailComponent,
    ProTenMuaUpdateComponent,
    ProTenMuaDeleteDialogComponent,
    ProTenMuaDeletePopupComponent
  ],
  entryComponents: [ProTenMuaDeleteDialogComponent]
})
export class CtProTenMuaModule {}
