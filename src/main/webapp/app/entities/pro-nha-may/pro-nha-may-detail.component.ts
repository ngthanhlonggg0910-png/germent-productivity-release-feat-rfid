import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProNhaMay } from 'app/shared/model/pro-nha-may.model';

@Component({
  selector: 'jhi-pro-nha-may-detail',
  templateUrl: './pro-nha-may-detail.component.html'
})
export class ProNhaMayDetailComponent implements OnInit {
  proNhaMay: IProNhaMay;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proNhaMay }) => {
      this.proNhaMay = proNhaMay;
    });
  }

  previousState() {
    window.history.back();
  }
}
