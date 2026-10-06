import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { IProKeHoachSanXuat } from 'app/shared/model/pro-ke-hoach-san-xuat.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ProKeHoachSanXuatService } from './pro-ke-hoach-san-xuat.service';

@Component({
  selector: 'jhi-pro-ke-hoach-san-xuat',
  templateUrl: './pro-ke-hoach-san-xuat.component.html'
})
export class ProKeHoachSanXuatComponent implements OnInit, OnDestroy {
  proKeHoachSanXuats: IProKeHoachSanXuat[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;

  constructor(
    protected proKeHoachSanXuatService: ProKeHoachSanXuatService,
    protected parseLinks: JhiParseLinks,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
    });
  }

  loadAll() {
    this.proKeHoachSanXuatService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IProKeHoachSanXuat[]>) => this.paginateProKeHoachSanXuats(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/pro-ke-hoach-san-xuat'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAll();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/pro-ke-hoach-san-xuat',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInProKeHoachSanXuats();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IProKeHoachSanXuat) {
    return item.id;
  }

  registerChangeInProKeHoachSanXuats() {
    this.eventSubscriber = this.eventManager.subscribe('proKeHoachSanXuatListModification', () => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateProKeHoachSanXuats(data: IProKeHoachSanXuat[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.proKeHoachSanXuats = data;
  }
}
