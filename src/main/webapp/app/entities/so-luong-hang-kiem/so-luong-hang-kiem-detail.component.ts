import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISoLuongHangKiem } from 'app/shared/model/so-luong-hang-kiem.model';

@Component({
  selector: 'jhi-so-luong-hang-kiem-detail',
  templateUrl: './so-luong-hang-kiem-detail.component.html'
})
export class SoLuongHangKiemDetailComponent implements OnInit {
  soLuongHangKiem: ISoLuongHangKiem;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ soLuongHangKiem }) => {
      this.soLuongHangKiem = soLuongHangKiem;
    });
  }

  previousState() {
    window.history.back();
  }
}
