import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProGioLamViec } from 'app/shared/model/pro-gio-lam-viec.model';

@Component({
  selector: 'jhi-pro-gio-lam-viec-detail',
  templateUrl: './pro-gio-lam-viec-detail.component.html'
})
export class ProGioLamViecDetailComponent implements OnInit {
  proGioLamViec: IProGioLamViec;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proGioLamViec }) => {
      this.proGioLamViec = proGioLamViec;
    });
  }

  previousState() {
    window.history.back();
  }
}
