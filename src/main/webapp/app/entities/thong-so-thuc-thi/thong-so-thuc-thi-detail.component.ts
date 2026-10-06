import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IThongSoThucThi } from 'app/shared/model/thong-so-thuc-thi.model';

@Component({
  selector: 'jhi-thong-so-thuc-thi-detail',
  templateUrl: './thong-so-thuc-thi-detail.component.html'
})
export class ThongSoThucThiDetailComponent implements OnInit {
  thongSoThucThi: IThongSoThucThi;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ thongSoThucThi }) => {
      this.thongSoThucThi = thongSoThucThi;
    });
  }

  previousState() {
    window.history.back();
  }
}
