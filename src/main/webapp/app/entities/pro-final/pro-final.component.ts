import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IProFinal } from 'app/shared/model/pro-final.model';
import { ProFinalService } from './pro-final.service';

@Component({
  selector: 'jhi-pro-final',
  templateUrl: './pro-final.component.html'
})
export class ProFinalComponent implements OnInit, OnDestroy {
  proFinals: IProFinal[];
  eventSubscriber: Subscription;

  constructor(protected proFinalService: ProFinalService, protected eventManager: JhiEventManager) {}

  loadAll() {
    this.proFinalService.query().subscribe((res: HttpResponse<IProFinal[]>) => {
      this.proFinals = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInProFinals();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IProFinal) {
    return item.id;
  }

  registerChangeInProFinals() {
    this.eventSubscriber = this.eventManager.subscribe('proFinalListModification', () => this.loadAll());
  }
}
