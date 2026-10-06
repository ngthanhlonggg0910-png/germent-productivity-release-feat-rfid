import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProIssue } from 'app/shared/model/pro-issue.model';

@Component({
  selector: 'jhi-pro-issue-detail',
  templateUrl: './pro-issue-detail.component.html'
})
export class ProIssueDetailComponent implements OnInit {
  proIssue: IProIssue;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ proIssue }) => {
      this.proIssue = proIssue;
    });
  }

  previousState() {
    window.history.back();
  }
}
