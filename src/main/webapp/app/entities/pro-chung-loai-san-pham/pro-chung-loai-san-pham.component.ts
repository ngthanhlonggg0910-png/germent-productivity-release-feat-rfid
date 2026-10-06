import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { IProChungLoaiSanPham } from 'app/shared/model/pro-chung-loai-san-pham.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ProChungLoaiSanPhamService } from './pro-chung-loai-san-pham.service';

@Component({
  selector: 'jhi-pro-chung-loai-san-pham',
  templateUrl: './pro-chung-loai-san-pham.component.html'
})
export class ProChungLoaiSanPhamComponent implements OnInit, OnDestroy {
  proChungLoaiSanPhams: IProChungLoaiSanPham[];
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
    protected proChungLoaiSanPhamService: ProChungLoaiSanPhamService,
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
    this.proChungLoaiSanPhamService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IProChungLoaiSanPham[]>) => this.paginateProChungLoaiSanPhams(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/pro-chung-loai-san-pham'], {
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
      '/pro-chung-loai-san-pham',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInProChungLoaiSanPhams();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IProChungLoaiSanPham) {
    return item.id;
  }

  registerChangeInProChungLoaiSanPhams() {
    this.eventSubscriber = this.eventManager.subscribe('proChungLoaiSanPhamListModification', () => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateProChungLoaiSanPhams(data: IProChungLoaiSanPham[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.proChungLoaiSanPhams = data;
  }
}
