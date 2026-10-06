import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IDoKimMd } from 'app/shared/model/do-kim-md.model';
import { DoKimMdService } from './do-kim-md.service';

@Component({
  selector: 'jhi-do-kim-md',
  templateUrl: './do-kim-md.component.html'
})
export class DoKimMdComponent implements OnInit, OnDestroy {
  doKimMds: IDoKimMd[];
  eventSubscriber: Subscription;

  constructor(protected doKimMdService: DoKimMdService, protected eventManager: JhiEventManager) {}

  loadAll() {
    this.doKimMdService.query().subscribe((res: HttpResponse<IDoKimMd[]>) => {
      this.doKimMds = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInDoKimMds();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IDoKimMd) {
    return item.id;
  }

  registerChangeInDoKimMds() {
    this.eventSubscriber = this.eventManager.subscribe('doKimMdListModification', () => this.loadAll());
  }
}
