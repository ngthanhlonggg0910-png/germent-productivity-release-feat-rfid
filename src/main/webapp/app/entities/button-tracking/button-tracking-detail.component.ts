import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IButtonTracking } from 'app/shared/model/button-tracking.model';

@Component({
  selector: 'jhi-button-tracking-detail',
  templateUrl: './button-tracking-detail.component.html'
})
export class ButtonTrackingDetailComponent implements OnInit {
  buttonTracking: IButtonTracking;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ buttonTracking }) => {
      this.buttonTracking = buttonTracking;
    });
  }

  previousState() {
    window.history.back();
  }
}
