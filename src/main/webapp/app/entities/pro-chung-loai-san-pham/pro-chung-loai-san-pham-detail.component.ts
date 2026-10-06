import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProChungLoaiSanPham } from 'app/shared/model/pro-chung-loai-san-pham.model';

@Component({
  selector: 'jhi-pro-chung-loai-san-pham-detail',
  templateUrl: './pro-chung-loai-san-pham-detail.component.html'
})
export class ProChungLoaiSanPhamDetailComponent implements OnInit {
  proChungLoaiSanPham: IProChungLoaiSanPham;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proChungLoaiSanPham }) => {
      this.proChungLoaiSanPham = proChungLoaiSanPham;
    });
  }

  previousState() {
    window.history.back();
  }
}
