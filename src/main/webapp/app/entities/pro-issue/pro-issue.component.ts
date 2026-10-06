import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IProIssue } from 'app/shared/model/pro-issue.model';
import { ProIssueService } from './pro-issue.service';

@Component({
  selector: 'jhi-pro-issue',
  templateUrl: './pro-issue.component.html'
})
export class ProIssueComponent implements OnInit, OnDestroy {
  proIssues: IProIssue[];
  eventSubscriber: Subscription;

  constructor(protected proIssueService: ProIssueService, protected eventManager: JhiEventManager) {}

  loadAll() {
    this.proIssueService.query().subscribe((res: HttpResponse<IProIssue[]>) => {
      this.proIssues = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInProIssues();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IProIssue) {
    return item.id;
  }

  registerChangeInProIssues() {
    this.eventSubscriber = this.eventManager.subscribe('proIssueListModification', () => this.loadAll());
  }
}
