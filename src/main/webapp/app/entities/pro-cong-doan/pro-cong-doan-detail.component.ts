import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProCongDoan } from 'app/shared/model/pro-cong-doan.model';

@Component({
  selector: 'jhi-pro-cong-doan-detail',
  templateUrl: './pro-cong-doan-detail.component.html'
})
export class ProCongDoanDetailComponent implements OnInit {
  proCongDoan: IProCongDoan;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proCongDoan }) => {
      this.proCongDoan = proCongDoan;
    });
  }

  previousState() {
    window.history.back();
  }
}
