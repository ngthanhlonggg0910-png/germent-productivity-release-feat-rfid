import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProRefinalCC } from 'app/shared/model/pro-refinal-cc.model';

@Component({
  selector: 'jhi-pro-refinal-cc-detail',
  templateUrl: './pro-refinal-cc-detail.component.html'
})
export class ProRefinalCCDetailComponent implements OnInit {
  proRefinalCC: IProRefinalCC;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proRefinalCC }) => {
      this.proRefinalCC = proRefinalCC;
    });
  }

  previousState() {
    window.history.back();
  }
}
