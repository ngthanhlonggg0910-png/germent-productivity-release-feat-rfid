import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CtSharedModule } from 'app/shared/shared.module';
import { GopYChatLuongComponent } from './gop-y-chat-luong.component';
import { GopYChatLuongDetailComponent } from './gop-y-chat-luong-detail.component';
import { GopYChatLuongUpdateComponent } from './gop-y-chat-luong-update.component';
import { GopYChatLuongDeletePopupComponent, GopYChatLuongDeleteDialogComponent } from './gop-y-chat-luong-delete-dialog.component';
import { gopYChatLuongRoute, gopYChatLuongPopupRoute } from './gop-y-chat-luong.route';

const ENTITY_STATES = [...gopYChatLuongRoute, ...gopYChatLuongPopupRoute];

@NgModule({
  imports: [CtSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    GopYChatLuongComponent,
    GopYChatLuongDetailComponent,
    GopYChatLuongUpdateComponent,
    GopYChatLuongDeleteDialogComponent,
    GopYChatLuongDeletePopupComponent
  ],
  entryComponents: [GopYChatLuongDeleteDialogComponent]
})
export class CtGopYChatLuongModule {}
