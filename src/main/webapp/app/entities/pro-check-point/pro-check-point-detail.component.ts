import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProCheckPoint } from 'app/shared/model/pro-check-point.model';

@Component({
  selector: 'jhi-pro-check-point-detail',
  templateUrl: './pro-check-point-detail.component.html'
})
export class ProCheckPointDetailComponent implements OnInit {
  proCheckPoint: IProCheckPoint;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proCheckPoint }) => {
      this.proCheckPoint = proCheckPoint;
    });
  }

  previousState() {
    window.history.back();
  }
}
