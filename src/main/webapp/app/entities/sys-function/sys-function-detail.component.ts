import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISysFunction } from 'app/shared/model/sys-function.model';

@Component({
  selector: 'jhi-sys-function-detail',
  templateUrl: './sys-function-detail.component.html'
})
export class SysFunctionDetailComponent implements OnInit {
  sysFunction: ISysFunction;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ sysFunction }) => {
      this.sysFunction = sysFunction;
    });
  }

  previousState() {
    window.history.back();
  }
}
