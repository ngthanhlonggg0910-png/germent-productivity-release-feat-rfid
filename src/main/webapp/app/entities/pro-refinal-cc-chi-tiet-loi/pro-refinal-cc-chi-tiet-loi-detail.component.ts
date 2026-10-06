import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProRefinalCCChiTietLoi } from 'app/shared/model/pro-refinal-cc-chi-tiet-loi.model';

@Component({
  selector: 'jhi-pro-refinal-cc-chi-tiet-loi-detail',
  templateUrl: './pro-refinal-cc-chi-tiet-loi-detail.component.html'
})
export class ProRefinalCCChiTietLoiDetailComponent implements OnInit {
  proRefinalCCChiTietLoi: IProRefinalCCChiTietLoi;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proRefinalCCChiTietLoi }) => {
      this.proRefinalCCChiTietLoi = proRefinalCCChiTietLoi;
    });
  }

  previousState() {
    window.history.back();
  }
}
