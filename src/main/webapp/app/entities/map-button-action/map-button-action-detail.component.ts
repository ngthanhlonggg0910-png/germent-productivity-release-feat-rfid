import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMapButtonAction } from 'app/shared/model/map-button-action.model';

@Component({
  selector: 'jhi-map-button-action-detail',
  templateUrl: './map-button-action-detail.component.html'
})
export class MapButtonActionDetailComponent implements OnInit {
  mapButtonAction: IMapButtonAction;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mapButtonAction }) => {
      this.mapButtonAction = mapButtonAction;
    });
  }

  previousState() {
    window.history.back();
  }
}
