import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IThongSoDoSP } from 'app/shared/model/thong-so-do-sp.model';

@Component({
  selector: 'jhi-thong-so-do-sp-detail',
  templateUrl: './thong-so-do-sp-detail.component.html'
})
export class ThongSoDoSPDetailComponent implements OnInit {
  thongSoDoSP: IThongSoDoSP;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ thongSoDoSP }) => {
      this.thongSoDoSP = thongSoDoSP;
    });
  }

  previousState() {
    window.history.back();
  }
}
