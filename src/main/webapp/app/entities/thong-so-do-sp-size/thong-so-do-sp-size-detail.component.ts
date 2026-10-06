import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IThongSoDoSPSize } from 'app/shared/model/thong-so-do-sp-size.model';

@Component({
  selector: 'jhi-thong-so-do-sp-size-detail',
  templateUrl: './thong-so-do-sp-size-detail.component.html'
})
export class ThongSoDoSPSizeDetailComponent implements OnInit {
  thongSoDoSPSize: IThongSoDoSPSize;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ thongSoDoSPSize }) => {
      this.thongSoDoSPSize = thongSoDoSPSize;
    });
  }

  previousState() {
    window.history.back();
  }
}
