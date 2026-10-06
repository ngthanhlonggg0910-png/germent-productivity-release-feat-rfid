import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { VideoComponent } from './video.component';
import { VideoDetailComponent } from './video-detail.component';
import { VideoUpdateComponent } from './video-update.component';
import { VideoDeletePopupComponent, VideoDeleteDialogComponent } from './video-delete-dialog.component';
import { videoRoute, videoPopupRoute } from './video.route';

const ENTITY_STATES = [...videoRoute, ...videoPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [VideoComponent, VideoDetailComponent, VideoUpdateComponent, VideoDeleteDialogComponent, VideoDeletePopupComponent],
  entryComponents: [VideoDeleteDialogComponent]
})
export class CtVideoModule {}
