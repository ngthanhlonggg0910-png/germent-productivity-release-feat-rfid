import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { ProGioLamViecComponent } from './pro-gio-lam-viec.component';
import { ProGioLamViecDetailComponent } from './pro-gio-lam-viec-detail.component';
import { ProGioLamViecUpdateComponent } from './pro-gio-lam-viec-update.component';
import { ProGioLamViecDeletePopupComponent, ProGioLamViecDeleteDialogComponent } from './pro-gio-lam-viec-delete-dialog.component';
import { proGioLamViecRoute, proGioLamViecPopupRoute } from './pro-gio-lam-viec.route';

const ENTITY_STATES = [...proGioLamViecRoute, ...proGioLamViecPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    ProGioLamViecComponent,
    ProGioLamViecDetailComponent,
    ProGioLamViecUpdateComponent,
    ProGioLamViecDeleteDialogComponent,
    ProGioLamViecDeletePopupComponent
  ],
  entryComponents: [ProGioLamViecDeleteDialogComponent]
})
export class CtProGioLamViecModule {}
