import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IProIssueCumLoi } from 'app/shared/model/pro-issue-cum-loi.model';
import { ProIssueCumLoiService } from './pro-issue-cum-loi.service';

@Component({
  selector: 'jhi-pro-issue-cum-loi',
  templateUrl: './pro-issue-cum-loi.component.html'
})
export class ProIssueCumLoiComponent implements OnInit, OnDestroy {
  proIssueCumLois: IProIssueCumLoi[];
  eventSubscriber: Subscription;

  constructor(protected proIssueCumLoiService: ProIssueCumLoiService, protected eventManager: JhiEventManager) {}

  loadAll() {
    this.proIssueCumLoiService.query().subscribe((res: HttpResponse<IProIssueCumLoi[]>) => {
      this.proIssueCumLois = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInProIssueCumLois();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IProIssueCumLoi) {
    return item.id;
  }

  registerChangeInProIssueCumLois() {
    this.eventSubscriber = this.eventManager.subscribe('proIssueCumLoiListModification', () => this.loadAll());
  }
}
