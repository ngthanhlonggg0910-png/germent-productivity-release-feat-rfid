import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProNhomCongDoan } from 'app/shared/model/pro-nhom-cong-doan.model';

@Component({
  selector: 'jhi-pro-nhom-cong-doan-detail',
  templateUrl: './pro-nhom-cong-doan-detail.component.html'
})
export class ProNhomCongDoanDetailComponent implements OnInit {
  proNhomCongDoan: IProNhomCongDoan;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proNhomCongDoan }) => {
      this.proNhomCongDoan = proNhomCongDoan;
    });
  }

  previousState() {
    window.history.back();
  }
}
