import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INVButtonTrackingType } from 'app/shared/model/nv-button-tracking-type.model';

@Component({
  selector: 'jhi-nv-button-tracking-type-detail',
  templateUrl: './nv-button-tracking-type-detail.component.html'
})
export class NVButtonTrackingTypeDetailComponent implements OnInit {
  nVButtonTrackingType: INVButtonTrackingType;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ nVButtonTrackingType }) => {
      this.nVButtonTrackingType = nVButtonTrackingType;
    });
  }

  previousState() {
    window.history.back();
  }
}
