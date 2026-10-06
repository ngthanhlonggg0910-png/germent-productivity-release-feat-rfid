import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILibSoLuongPoKiem } from 'app/shared/model/lib-so-luong-po-kiem.model';

@Component({
  selector: 'jhi-lib-so-luong-po-kiem-detail',
  templateUrl: './lib-so-luong-po-kiem-detail.component.html'
})
export class LibSoLuongPoKiemDetailComponent implements OnInit {
  libSoLuongPoKiem: ILibSoLuongPoKiem;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ libSoLuongPoKiem }) => {
      this.libSoLuongPoKiem = libSoLuongPoKiem;
    });
  }

  previousState() {
    window.history.back();
  }
}
