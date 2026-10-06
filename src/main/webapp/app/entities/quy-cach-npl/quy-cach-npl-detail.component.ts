import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQuyCachNPL } from 'app/shared/model/quy-cach-npl.model';

@Component({
  selector: 'jhi-quy-cach-npl-detail',
  templateUrl: './quy-cach-npl-detail.component.html'
})
export class QuyCachNPLDetailComponent implements OnInit {
  quyCachNPL: IQuyCachNPL;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ quyCachNPL }) => {
      this.quyCachNPL = quyCachNPL;
    });
  }

  previousState() {
    window.history.back();
  }
}
