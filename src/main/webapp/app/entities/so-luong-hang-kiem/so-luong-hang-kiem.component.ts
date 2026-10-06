import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ISoLuongHangKiem } from 'app/shared/model/so-luong-hang-kiem.model';
import { SoLuongHangKiemService } from './so-luong-hang-kiem.service';

@Component({
  selector: 'jhi-so-luong-hang-kiem',
  templateUrl: './so-luong-hang-kiem.component.html'
})
export class SoLuongHangKiemComponent implements OnInit, OnDestroy {
  soLuongHangKiems: ISoLuongHangKiem[];
  eventSubscriber: Subscription;

  constructor(protected soLuongHangKiemService: SoLuongHangKiemService, protected eventManager: JhiEventManager) {}

  loadAll() {
    this.soLuongHangKiemService.query().subscribe((res: HttpResponse<ISoLuongHangKiem[]>) => {
      this.soLuongHangKiems = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInSoLuongHangKiems();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ISoLuongHangKiem) {
    return item.id;
  }

  registerChangeInSoLuongHangKiems() {
    this.eventSubscriber = this.eventManager.subscribe('soLuongHangKiemListModification', () => this.loadAll());
  }
}
