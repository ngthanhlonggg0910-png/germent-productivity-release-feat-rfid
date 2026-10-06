import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IProRefinal } from 'app/shared/model/pro-refinal.model';
import { ProRefinalService } from './pro-refinal.service';

@Component({
  selector: 'jhi-pro-refinal',
  templateUrl: './pro-refinal.component.html'
})
export class ProRefinalComponent implements OnInit, OnDestroy {
  proRefinals: IProRefinal[];
  eventSubscriber: Subscription;

  constructor(protected proRefinalService: ProRefinalService, protected eventManager: JhiEventManager) {}

  loadAll() {
    this.proRefinalService.query().subscribe((res: HttpResponse<IProRefinal[]>) => {
      this.proRefinals = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInProRefinals();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IProRefinal) {
    return item.id;
  }

  registerChangeInProRefinals() {
    this.eventSubscriber = this.eventManager.subscribe('proRefinalListModification', () => this.loadAll());
  }
}
