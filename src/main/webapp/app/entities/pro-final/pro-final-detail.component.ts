import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProFinal } from 'app/shared/model/pro-final.model';

@Component({
  selector: 'jhi-pro-final-detail',
  templateUrl: './pro-final-detail.component.html'
})
export class ProFinalDetailComponent implements OnInit {
  proFinal: IProFinal;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proFinal }) => {
      this.proFinal = proFinal;
    });
  }

  previousState() {
    window.history.back();
  }
}
