import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGYCLKhacPhuc } from 'app/shared/model/gycl-khac-phuc.model';

@Component({
  selector: 'jhi-gycl-khac-phuc-detail',
  templateUrl: './gycl-khac-phuc-detail.component.html'
})
export class GYCLKhacPhucDetailComponent implements OnInit {
  gYCLKhacPhuc: IGYCLKhacPhuc;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ gYCLKhacPhuc }) => {
      this.gYCLKhacPhuc = gYCLKhacPhuc;
    });
  }

  previousState() {
    window.history.back();
  }
}
