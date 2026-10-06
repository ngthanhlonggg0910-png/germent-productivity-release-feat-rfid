import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ICauHinhZalo } from 'app/shared/model/cau-hinh-zalo.model';
import { CauHinhZaloService } from './cau-hinh-zalo.service';

@Component({
  selector: 'jhi-cau-hinh-zalo',
  templateUrl: './cau-hinh-zalo.component.html'
})
export class CauHinhZaloComponent implements OnInit, OnDestroy {
  cauHinhZalos: ICauHinhZalo[];
  eventSubscriber: Subscription;

  constructor(protected cauHinhZaloService: CauHinhZaloService, protected eventManager: JhiEventManager) {}

  loadAll() {
    this.cauHinhZaloService.query().subscribe((res: HttpResponse<ICauHinhZalo[]>) => {
      this.cauHinhZalos = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInCauHinhZalos();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ICauHinhZalo) {
    return item.id;
  }

  registerChangeInCauHinhZalos() {
    this.eventSubscriber = this.eventManager.subscribe('cauHinhZaloListModification', () => this.loadAll());
  }
}
