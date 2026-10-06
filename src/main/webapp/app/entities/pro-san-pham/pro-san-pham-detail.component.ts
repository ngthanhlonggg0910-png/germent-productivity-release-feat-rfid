import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProSanPham } from 'app/shared/model/pro-san-pham.model';

@Component({
  selector: 'jhi-pro-san-pham-detail',
  templateUrl: './pro-san-pham-detail.component.html'
})
export class ProSanPhamDetailComponent implements OnInit {
  proSanPham: IProSanPham;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proSanPham }) => {
      this.proSanPham = proSanPham;
    });
  }

  previousState() {
    window.history.back();
  }
}
