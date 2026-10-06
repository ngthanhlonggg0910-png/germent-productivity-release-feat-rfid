import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProNSCLChiTietLoi } from 'app/shared/model/pro-nscl-chi-tiet-loi.model';

@Component({
  selector: 'jhi-pro-nscl-chi-tiet-loi-detail',
  templateUrl: './pro-nscl-chi-tiet-loi-detail.component.html'
})
export class ProNSCLChiTietLoiDetailComponent implements OnInit {
  proNSCLChiTietLoi: IProNSCLChiTietLoi;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proNSCLChiTietLoi }) => {
      this.proNSCLChiTietLoi = proNSCLChiTietLoi;
    });
  }

  previousState() {
    window.history.back();
  }
}
