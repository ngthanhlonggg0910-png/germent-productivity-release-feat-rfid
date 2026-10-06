import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProTaiKhoanCheckPoint } from 'app/shared/model/pro-tai-khoan-check-point.model';

@Component({
  selector: 'jhi-pro-tai-khoan-check-point-detail',
  templateUrl: './pro-tai-khoan-check-point-detail.component.html'
})
export class ProTaiKhoanCheckPointDetailComponent implements OnInit {
  proTaiKhoanCheckPoint: IProTaiKhoanCheckPoint;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proTaiKhoanCheckPoint }) => {
      this.proTaiKhoanCheckPoint = proTaiKhoanCheckPoint;
    });
  }

  previousState() {
    window.history.back();
  }
}
