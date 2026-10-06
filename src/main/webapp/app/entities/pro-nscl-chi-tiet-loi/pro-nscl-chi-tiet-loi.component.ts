import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { IProNSCLChiTietLoi } from 'app/shared/model/pro-nscl-chi-tiet-loi.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ProNSCLChiTietLoiService } from './pro-nscl-chi-tiet-loi.service';

@Component({
  selector: 'jhi-pro-nscl-chi-tiet-loi',
  templateUrl: './pro-nscl-chi-tiet-loi.component.html'
})
export class ProNSCLChiTietLoiComponent implements OnInit, OnDestroy {
  proNSCLChiTietLois: IProNSCLChiTietLoi[];
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
    protected proNSCLChiTietLoiService: ProNSCLChiTietLoiService,
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
    this.proNSCLChiTietLoiService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IProNSCLChiTietLoi[]>) => this.paginateProNSCLChiTietLois(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/pro-nscl-chi-tiet-loi'], {
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
      '/pro-nscl-chi-tiet-loi',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInProNSCLChiTietLois();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IProNSCLChiTietLoi) {
    return item.id;
  }

  registerChangeInProNSCLChiTietLois() {
    this.eventSubscriber = this.eventManager.subscribe('proNSCLChiTietLoiListModification', () => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateProNSCLChiTietLois(data: IProNSCLChiTietLoi[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.proNSCLChiTietLois = data;
  }
}
