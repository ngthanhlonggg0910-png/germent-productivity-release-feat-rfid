import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProCumLoi } from 'app/shared/model/pro-cum-loi.model';

@Component({
  selector: 'jhi-pro-cum-loi-detail',
  templateUrl: './pro-cum-loi-detail.component.html'
})
export class ProCumLoiDetailComponent implements OnInit {
  proCumLoi: IProCumLoi;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proCumLoi }) => {
      this.proCumLoi = proCumLoi;
    });
  }

  previousState() {
    window.history.back();
  }
}
