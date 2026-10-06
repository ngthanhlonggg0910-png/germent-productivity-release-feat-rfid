import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProLoaiChuyen } from 'app/shared/model/pro-loai-chuyen.model';

@Component({
  selector: 'jhi-pro-loai-chuyen-detail',
  templateUrl: './pro-loai-chuyen-detail.component.html'
})
export class ProLoaiChuyenDetailComponent implements OnInit {
  proLoaiChuyen: IProLoaiChuyen;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proLoaiChuyen }) => {
      this.proLoaiChuyen = proLoaiChuyen;
    });
  }

  previousState() {
    window.history.back();
  }
}
