import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProKeHoachSanXuat } from 'app/shared/model/pro-ke-hoach-san-xuat.model';

@Component({
  selector: 'jhi-pro-ke-hoach-san-xuat-detail',
  templateUrl: './pro-ke-hoach-san-xuat-detail.component.html'
})
export class ProKeHoachSanXuatDetailComponent implements OnInit {
  proKeHoachSanXuat: IProKeHoachSanXuat;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proKeHoachSanXuat }) => {
      this.proKeHoachSanXuat = proKeHoachSanXuat;
    });
  }

  previousState() {
    window.history.back();
  }
}
