import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProAql } from 'app/shared/model/pro-aql.model';

@Component({
  selector: 'jhi-pro-aql-detail',
  templateUrl: './pro-aql-detail.component.html'
})
export class ProAqlDetailComponent implements OnInit {
  proAql: IProAql;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proAql }) => {
      this.proAql = proAql;
    });
  }

  previousState() {
    window.history.back();
  }
}
