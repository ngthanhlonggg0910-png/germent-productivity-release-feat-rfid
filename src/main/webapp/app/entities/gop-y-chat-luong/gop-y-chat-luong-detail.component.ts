import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGopYChatLuong } from 'app/shared/model/gop-y-chat-luong.model';

@Component({
  selector: 'jhi-gop-y-chat-luong-detail',
  templateUrl: './gop-y-chat-luong-detail.component.html'
})
export class GopYChatLuongDetailComponent implements OnInit {
  gopYChatLuong: IGopYChatLuong;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ gopYChatLuong }) => {
      this.gopYChatLuong = gopYChatLuong;
    });
  }

  previousState() {
    window.history.back();
  }
}
