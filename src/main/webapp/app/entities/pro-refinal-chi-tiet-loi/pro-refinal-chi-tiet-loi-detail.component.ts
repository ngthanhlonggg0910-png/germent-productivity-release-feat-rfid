import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProRefinalChiTietLoi } from 'app/shared/model/pro-refinal-chi-tiet-loi.model';

@Component({
  selector: 'jhi-pro-refinal-chi-tiet-loi-detail',
  templateUrl: './pro-refinal-chi-tiet-loi-detail.component.html'
})
export class ProRefinalChiTietLoiDetailComponent implements OnInit {
  proRefinalChiTietLoi: IProRefinalChiTietLoi;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proRefinalChiTietLoi }) => {
      this.proRefinalChiTietLoi = proRefinalChiTietLoi;
    });
  }

  previousState() {
    window.history.back();
  }
}
