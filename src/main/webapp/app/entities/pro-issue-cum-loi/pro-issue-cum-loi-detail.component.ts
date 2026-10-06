import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProIssueCumLoi } from 'app/shared/model/pro-issue-cum-loi.model';

@Component({
  selector: 'jhi-pro-issue-cum-loi-detail',
  templateUrl: './pro-issue-cum-loi-detail.component.html'
})
export class ProIssueCumLoiDetailComponent implements OnInit {
  proIssueCumLoi: IProIssueCumLoi;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proIssueCumLoi }) => {
      this.proIssueCumLoi = proIssueCumLoi;
    });
  }

  previousState() {
    window.history.back();
  }
}
