import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKhacPhucLoi } from 'app/shared/model/khac-phuc-loi.model';

@Component({
  selector: 'jhi-khac-phuc-loi-detail',
  templateUrl: './khac-phuc-loi-detail.component.html'
})
export class KhacPhucLoiDetailComponent implements OnInit {
  khacPhucLoi: IKhacPhucLoi;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ khacPhucLoi }) => {
      this.khacPhucLoi = khacPhucLoi;
    });
  }

  previousState() {
    window.history.back();
  }
}
