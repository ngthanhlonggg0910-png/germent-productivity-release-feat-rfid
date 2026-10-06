import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProIssue } from 'app/shared/model/pro-issue.model';

type EntityResponseType = HttpResponse<IProIssue>;
type EntityArrayResponseType = HttpResponse<IProIssue[]>;

@Injectable({ providedIn: 'root' })
export class ProIssueService {
  public resourceUrl = SERVER_API_URL + 'api/pro-issues';

  constructor(protected http: HttpClient) {}

  create(proIssue: IProIssue): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proIssue);
    return this.http
      .post<IProIssue>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(proIssue: IProIssue): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proIssue);
    return this.http
      .put<IProIssue>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IProIssue>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProIssue[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(proIssue: IProIssue): IProIssue {
    const copy: IProIssue = Object.assign({}, proIssue, {
      ngayGioBatDau: proIssue.ngayGioBatDau != null && proIssue.ngayGioBatDau.isValid() ? proIssue.ngayGioBatDau.toJSON() : null,
      ngayGioKetThuc: proIssue.ngayGioKetThuc != null && proIssue.ngayGioKetThuc.isValid() ? proIssue.ngayGioKetThuc.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ngayGioBatDau = res.body.ngayGioBatDau != null ? moment(res.body.ngayGioBatDau) : null;
      res.body.ngayGioKetThuc = res.body.ngayGioKetThuc != null ? moment(res.body.ngayGioKetThuc) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((proIssue: IProIssue) => {
        proIssue.ngayGioBatDau = proIssue.ngayGioBatDau != null ? moment(proIssue.ngayGioBatDau) : null;
        proIssue.ngayGioKetThuc = proIssue.ngayGioKetThuc != null ? moment(proIssue.ngayGioKetThuc) : null;
      });
    }
    return res;
  }
}
