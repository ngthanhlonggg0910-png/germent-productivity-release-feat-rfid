import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IProIssueLoi } from 'app/shared/model/pro-issue-loi.model';
import { ProIssueLoiService } from './pro-issue-loi.service';

@Component({
  selector: 'jhi-pro-issue-loi',
  templateUrl: './pro-issue-loi.component.html'
})
export class ProIssueLoiComponent implements OnInit, OnDestroy {
  proIssueLois: IProIssueLoi[];
  eventSubscriber: Subscription;

  constructor(protected proIssueLoiService: ProIssueLoiService, protected eventManager: JhiEventManager) {}

  loadAll() {
    this.proIssueLoiService.query().subscribe((res: HttpResponse<IProIssueLoi[]>) => {
      this.proIssueLois = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInProIssueLois();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IProIssueLoi) {
    return item.id;
  }

  registerChangeInProIssueLois() {
    this.eventSubscriber = this.eventManager.subscribe('proIssueLoiListModification', () => this.loadAll());
  }
}
