import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProRefinal } from 'app/shared/model/pro-refinal.model';

@Component({
  selector: 'jhi-pro-refinal-detail',
  templateUrl: './pro-refinal-detail.component.html'
})
export class ProRefinalDetailComponent implements OnInit {
  proRefinal: IProRefinal;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proRefinal }) => {
      this.proRefinal = proRefinal;
    });
  }

  previousState() {
    window.history.back();
  }
}
