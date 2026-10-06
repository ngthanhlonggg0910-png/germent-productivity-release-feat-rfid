import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProKhachHang } from 'app/shared/model/pro-khach-hang.model';

@Component({
  selector: 'jhi-pro-khach-hang-detail',
  templateUrl: './pro-khach-hang-detail.component.html'
})
export class ProKhachHangDetailComponent implements OnInit {
  proKhachHang: IProKhachHang;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proKhachHang }) => {
      this.proKhachHang = proKhachHang;
    });
  }

  previousState() {
    window.history.back();
  }
}
