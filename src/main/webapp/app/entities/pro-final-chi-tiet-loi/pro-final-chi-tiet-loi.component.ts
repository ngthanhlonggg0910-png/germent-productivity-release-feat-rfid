import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { IProFinalChiTietLoi } from 'app/shared/model/pro-final-chi-tiet-loi.model';
import { ProFinalChiTietLoiService } from './pro-final-chi-tiet-loi.service';

@Component({
  selector: 'jhi-pro-final-chi-tiet-loi',
  templateUrl: './pro-final-chi-tiet-loi.component.html'
})
export class ProFinalChiTietLoiComponent implements OnInit, OnDestroy {
  proFinalChiTietLois: IProFinalChiTietLoi[];
  eventSubscriber: Subscription;

  constructor(protected proFinalChiTietLoiService: ProFinalChiTietLoiService, protected eventManager: JhiEventManager) {}

  loadAll() {
    this.proFinalChiTietLoiService.query().subscribe((res: HttpResponse<IProFinalChiTietLoi[]>) => {
      this.proFinalChiTietLois = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInProFinalChiTietLois();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IProFinalChiTietLoi) {
    return item.id;
  }

  registerChangeInProFinalChiTietLois() {
    this.eventSubscriber = this.eventManager.subscribe('proFinalChiTietLoiListModification', () => this.loadAll());
  }
}
