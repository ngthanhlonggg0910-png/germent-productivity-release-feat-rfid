import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IProRefinalChiTietLoi } from 'app/shared/model/pro-refinal-chi-tiet-loi.model';
import { ProRefinalChiTietLoiService } from './pro-refinal-chi-tiet-loi.service';

@Component({
  selector: 'jhi-pro-refinal-chi-tiet-loi',
  templateUrl: './pro-refinal-chi-tiet-loi.component.html'
})
export class ProRefinalChiTietLoiComponent implements OnInit, OnDestroy {
  proRefinalChiTietLois: IProRefinalChiTietLoi[];
  eventSubscriber: Subscription;

  constructor(protected proRefinalChiTietLoiService: ProRefinalChiTietLoiService, protected eventManager: JhiEventManager) {}

  loadAll() {
    this.proRefinalChiTietLoiService.query().subscribe((res: HttpResponse<IProRefinalChiTietLoi[]>) => {
      this.proRefinalChiTietLois = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInProRefinalChiTietLois();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IProRefinalChiTietLoi) {
    return item.id;
  }

  registerChangeInProRefinalChiTietLois() {
    this.eventSubscriber = this.eventManager.subscribe('proRefinalChiTietLoiListModification', () => this.loadAll());
  }
}
