import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { IGYCLQuyCachNPL } from 'app/shared/model/gycl-quy-cach-npl.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { GYCLQuyCachNPLService } from './gycl-quy-cach-npl.service';

@Component({
  selector: 'jhi-gycl-quy-cach-npl',
  templateUrl: './gycl-quy-cach-npl.component.html'
})
export class GYCLQuyCachNPLComponent implements OnInit, OnDestroy {
  gYCLQuyCachNPLS: IGYCLQuyCachNPL[];
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
    protected gYCLQuyCachNPLService: GYCLQuyCachNPLService,
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
    this.gYCLQuyCachNPLService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IGYCLQuyCachNPL[]>) => this.paginateGYCLQuyCachNPLS(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/gycl-quy-cach-npl'], {
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
      '/gycl-quy-cach-npl',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInGYCLQuyCachNPLS();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IGYCLQuyCachNPL) {
    return item.id;
  }

  registerChangeInGYCLQuyCachNPLS() {
    this.eventSubscriber = this.eventManager.subscribe('gYCLQuyCachNPLListModification', () => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateGYCLQuyCachNPLS(data: IGYCLQuyCachNPL[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.gYCLQuyCachNPLS = data;
  }
}
