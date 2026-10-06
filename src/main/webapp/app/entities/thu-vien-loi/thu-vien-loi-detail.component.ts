import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IThuVienLoi } from 'app/shared/model/thu-vien-loi.model';

@Component({
  selector: 'jhi-thu-vien-loi-detail',
  templateUrl: './thu-vien-loi-detail.component.html'
})
export class ThuVienLoiDetailComponent implements OnInit {
  thuVienLoi: IThuVienLoi;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ thuVienLoi }) => {
      this.thuVienLoi = thuVienLoi;
    });
  }

  previousState() {
    window.history.back();
  }
}
