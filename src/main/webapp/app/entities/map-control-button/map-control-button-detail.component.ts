import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMapControlButton } from 'app/shared/model/map-control-button.model';

@Component({
  selector: 'jhi-map-control-button-detail',
  templateUrl: './map-control-button-detail.component.html'
})
export class MapControlButtonDetailComponent implements OnInit {
  mapControlButton: IMapControlButton;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mapControlButton }) => {
      this.mapControlButton = mapControlButton;
    });
  }

  previousState() {
    window.history.back();
  }
}
