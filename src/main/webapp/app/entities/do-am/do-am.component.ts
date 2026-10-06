import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IDoAm } from 'app/shared/model/do-am.model';
import { DoAmService } from './do-am.service';

@Component({
  selector: 'jhi-do-am',
  templateUrl: './do-am.component.html'
})
export class DoAmComponent implements OnInit, OnDestroy {
  doAms: IDoAm[];
  eventSubscriber: Subscription;

  constructor(protected doAmService: DoAmService, protected eventManager: JhiEventManager) {}

  loadAll() {
    this.doAmService.query().subscribe((res: HttpResponse<IDoAm[]>) => {
      this.doAms = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInDoAms();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IDoAm) {
    return item.id;
  }

  registerChangeInDoAms() {
    this.eventSubscriber = this.eventManager.subscribe('doAmListModification', () => this.loadAll());
  }
}
