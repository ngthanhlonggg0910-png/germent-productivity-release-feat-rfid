import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProNSCLComponent } from './pro-nscl.component';
import { ProNSCLDetailComponent } from './pro-nscl-detail.component';
import { ProNSCLUpdateComponent } from './pro-nscl-update.component';
import { ProNSCLDeleteDialogComponent } from './pro-nscl-delete-dialog.component';
import { proNSCLRoute } from './pro-nscl.route';

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(proNSCLRoute)],
  declarations: [ProNSCLComponent, ProNSCLDetailComponent, ProNSCLUpdateComponent, ProNSCLDeleteDialogComponent],
  entryComponents: [ProNSCLDeleteDialogComponent]
})
export class CtProNSCLModule {}
