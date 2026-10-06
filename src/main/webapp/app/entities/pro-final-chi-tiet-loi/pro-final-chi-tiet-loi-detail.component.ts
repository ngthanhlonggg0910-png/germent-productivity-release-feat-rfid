import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProFinalChiTietLoi } from 'app/shared/model/pro-final-chi-tiet-loi.model';

@Component({
  selector: 'jhi-pro-final-chi-tiet-loi-detail',
  templateUrl: './pro-final-chi-tiet-loi-detail.component.html'
})
export class ProFinalChiTietLoiDetailComponent implements OnInit {
  proFinalChiTietLoi: IProFinalChiTietLoi;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proFinalChiTietLoi }) => {
      this.proFinalChiTietLoi = proFinalChiTietLoi;
    });
  }

  previousState() {
    window.history.back();
  }
}
