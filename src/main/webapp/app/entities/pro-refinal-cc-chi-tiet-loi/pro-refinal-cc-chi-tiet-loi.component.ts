import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IProRefinalCCChiTietLoi } from 'app/shared/model/pro-refinal-cc-chi-tiet-loi.model';
import { ProRefinalCCChiTietLoiService } from './pro-refinal-cc-chi-tiet-loi.service';

@Component({
  selector: 'jhi-pro-refinal-cc-chi-tiet-loi',
  templateUrl: './pro-refinal-cc-chi-tiet-loi.component.html'
})
export class ProRefinalCCChiTietLoiComponent implements OnInit, OnDestroy {
  proRefinalCCChiTietLois: IProRefinalCCChiTietLoi[];
  eventSubscriber: Subscription;

  constructor(protected proRefinalCCChiTietLoiService: ProRefinalCCChiTietLoiService, protected eventManager: JhiEventManager) {}

  loadAll() {
    this.proRefinalCCChiTietLoiService.query().subscribe((res: HttpResponse<IProRefinalCCChiTietLoi[]>) => {
      this.proRefinalCCChiTietLois = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInProRefinalCCChiTietLois();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IProRefinalCCChiTietLoi) {
    return item.id;
  }

  registerChangeInProRefinalCCChiTietLois() {
    this.eventSubscriber = this.eventManager.subscribe('proRefinalCCChiTietLoiListModification', () => this.loadAll());
  }
}
