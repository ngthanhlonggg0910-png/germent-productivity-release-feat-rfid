import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProLoi } from 'app/shared/model/pro-loi.model';

@Component({
  selector: 'jhi-pro-loi-detail',
  templateUrl: './pro-loi-detail.component.html'
})
export class ProLoiDetailComponent implements OnInit {
  proLoi: IProLoi;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proLoi }) => {
      this.proLoi = proLoi;
    });
  }

  previousState() {
    window.history.back();
  }
}
