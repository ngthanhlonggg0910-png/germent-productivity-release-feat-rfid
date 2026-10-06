import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProChuyen } from 'app/shared/model/pro-chuyen.model';

@Component({
  selector: 'jhi-pro-chuyen-detail',
  templateUrl: './pro-chuyen-detail.component.html'
})
export class ProChuyenDetailComponent implements OnInit {
  proChuyen: IProChuyen;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proChuyen }) => {
      this.proChuyen = proChuyen;
    });
  }

  previousState() {
    window.history.back();
  }
}
