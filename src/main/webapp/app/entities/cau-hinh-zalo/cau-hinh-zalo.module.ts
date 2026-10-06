import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { CauHinhZaloComponent } from './cau-hinh-zalo.component';
import { CauHinhZaloDetailComponent } from './cau-hinh-zalo-detail.component';
import { cauHinhZaloRoute, cauHinhZaloPopupRoute } from './cau-hinh-zalo.route';

const ENTITY_STATES = [...cauHinhZaloRoute, ...cauHinhZaloPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [CauHinhZaloComponent, CauHinhZaloDetailComponent],
  entryComponents: []
})
export class CtCauHinhZaloModule {}
