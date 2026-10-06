import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGYCLQuyCachNPL } from 'app/shared/model/gycl-quy-cach-npl.model';

@Component({
  selector: 'jhi-gycl-quy-cach-npl-detail',
  templateUrl: './gycl-quy-cach-npl-detail.component.html'
})
export class GYCLQuyCachNPLDetailComponent implements OnInit {
  gYCLQuyCachNPL: IGYCLQuyCachNPL;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ gYCLQuyCachNPL }) => {
      this.gYCLQuyCachNPL = gYCLQuyCachNPL;
    });
  }

  previousState() {
    window.history.back();
  }
}
