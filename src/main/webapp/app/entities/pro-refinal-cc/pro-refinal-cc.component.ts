import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IProRefinalCC } from 'app/shared/model/pro-refinal-cc.model';
import { ProRefinalCCService } from './pro-refinal-cc.service';

@Component({
  selector: 'jhi-pro-refinal-cc',
  templateUrl: './pro-refinal-cc.component.html'
})
export class ProRefinalCCComponent implements OnInit, OnDestroy {
  proRefinalCCS: IProRefinalCC[];
  eventSubscriber: Subscription;

  constructor(protected proRefinalCCService: ProRefinalCCService, protected eventManager: JhiEventManager) {}

  loadAll() {
    this.proRefinalCCService.query().subscribe((res: HttpResponse<IProRefinalCC[]>) => {
      this.proRefinalCCS = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInProRefinalCCS();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IProRefinalCC) {
    return item.id;
  }

  registerChangeInProRefinalCCS() {
    this.eventSubscriber = this.eventManager.subscribe('proRefinalCCListModification', () => this.loadAll());
  }
}
