import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDoAm } from 'app/shared/model/do-am.model';

@Component({
  selector: 'jhi-do-am-detail',
  templateUrl: './do-am-detail.component.html'
})
export class DoAmDetailComponent implements OnInit {
  doAm: IDoAm;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ doAm }) => {
      this.doAm = doAm;
    });
  }

  previousState() {
    window.history.back();
  }
}
