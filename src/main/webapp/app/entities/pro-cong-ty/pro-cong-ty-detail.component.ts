import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProCongTy } from 'app/shared/model/pro-cong-ty.model';

@Component({
  selector: 'jhi-pro-cong-ty-detail',
  templateUrl: './pro-cong-ty-detail.component.html'
})
export class ProCongTyDetailComponent implements OnInit {
  proCongTy: IProCongTy;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proCongTy }) => {
      this.proCongTy = proCongTy;
    });
  }

  previousState() {
    window.history.back();
  }
}
