import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { IProTaiKhoanCheckPoint } from 'app/shared/model/pro-tai-khoan-check-point.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { ProTaiKhoanCheckPointService } from './pro-tai-khoan-check-point.service';

@Component({
  selector: 'jhi-pro-tai-khoan-check-point',
  templateUrl: './pro-tai-khoan-check-point.component.html'
})
export class ProTaiKhoanCheckPointComponent implements OnInit, OnDestroy {
  proTaiKhoanCheckPoints: IProTaiKhoanCheckPoint[];
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
    protected proTaiKhoanCheckPointService: ProTaiKhoanCheckPointService,
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
    this.proTaiKhoanCheckPointService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IProTaiKhoanCheckPoint[]>) => this.paginateProTaiKhoanCheckPoints(res.body, res.headers));
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/pro-tai-khoan-check-point'], {
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
      '/pro-tai-khoan-check-point',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.registerChangeInProTaiKhoanCheckPoints();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IProTaiKhoanCheckPoint) {
    return item.id;
  }

  registerChangeInProTaiKhoanCheckPoints() {
    this.eventSubscriber = this.eventManager.subscribe('proTaiKhoanCheckPointListModification', () => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateProTaiKhoanCheckPoints(data: IProTaiKhoanCheckPoint[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.proTaiKhoanCheckPoints = data;
  }
}
