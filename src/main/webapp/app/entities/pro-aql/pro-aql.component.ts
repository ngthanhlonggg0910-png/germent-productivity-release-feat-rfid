import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IProAql } from 'app/shared/model/pro-aql.model';
import { ProAqlService } from './pro-aql.service';

@Component({
  selector: 'jhi-pro-aql',
  templateUrl: './pro-aql.component.html'
})
export class ProAqlComponent implements OnInit, OnDestroy {
  proAqls: IProAql[];
  eventSubscriber: Subscription;

  constructor(protected proAqlService: ProAqlService, protected eventManager: JhiEventManager) {}

  loadAll() {
    this.proAqlService.query().subscribe((res: HttpResponse<IProAql[]>) => {
      this.proAqls = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInProAqls();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IProAql) {
    return item.id;
  }

  registerChangeInProAqls() {
    this.eventSubscriber = this.eventManager.subscribe('proAqlListModification', () => this.loadAll());
  }
}
