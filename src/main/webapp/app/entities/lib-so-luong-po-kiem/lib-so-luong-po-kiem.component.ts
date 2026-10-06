import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ILibSoLuongPoKiem } from 'app/shared/model/lib-so-luong-po-kiem.model';
import { LibSoLuongPoKiemService } from './lib-so-luong-po-kiem.service';

@Component({
  selector: 'jhi-lib-so-luong-po-kiem',
  templateUrl: './lib-so-luong-po-kiem.component.html'
})
export class LibSoLuongPoKiemComponent implements OnInit, OnDestroy {
  libSoLuongPoKiems: ILibSoLuongPoKiem[];
  eventSubscriber: Subscription;

  constructor(protected libSoLuongPoKiemService: LibSoLuongPoKiemService, protected eventManager: JhiEventManager) {}

  loadAll() {
    this.libSoLuongPoKiemService.query().subscribe((res: HttpResponse<ILibSoLuongPoKiem[]>) => {
      this.libSoLuongPoKiems = res.body;
    });
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInLibSoLuongPoKiems();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ILibSoLuongPoKiem) {
    return item.id;
  }

  registerChangeInLibSoLuongPoKiems() {
    this.eventSubscriber = this.eventManager.subscribe('libSoLuongPoKiemListModification', () => this.loadAll());
  }
}
