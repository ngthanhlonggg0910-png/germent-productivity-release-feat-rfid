import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProIssueLoi } from 'app/shared/model/pro-issue-loi.model';

@Component({
  selector: 'jhi-pro-issue-loi-detail',
  templateUrl: './pro-issue-loi-detail.component.html'
})
export class ProIssueLoiDetailComponent implements OnInit {
  proIssueLoi: IProIssueLoi;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proIssueLoi }) => {
      this.proIssueLoi = proIssueLoi;
    });
  }

  previousState() {
    window.history.back();
  }
}
