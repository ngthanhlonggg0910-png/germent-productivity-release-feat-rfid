import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INVButton } from 'app/shared/model/nv-button.model';

@Component({
  selector: 'jhi-nv-button-detail',
  templateUrl: './nv-button-detail.component.html'
})
export class NVButtonDetailComponent implements OnInit {
  nVButton: INVButton;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ nVButton }) => {
      this.nVButton = nVButton;
    });
  }

  previousState() {
    window.history.back();
  }
}
