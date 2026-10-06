import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProRefinal } from 'app/shared/model/pro-refinal.model';

type EntityResponseType = HttpResponse<IProRefinal>;
type EntityArrayResponseType = HttpResponse<IProRefinal[]>;

@Injectable({ providedIn: 'root' })
export class ProRefinalService {
  public resourceUrl = SERVER_API_URL + 'api/pro-refinals';

  constructor(protected http: HttpClient) {}

  create(proRefinal: IProRefinal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proRefinal);
    return this.http
      .post<IProRefinal>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(proRefinal: IProRefinal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proRefinal);
    return this.http
      .put<IProRefinal>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IProRefinal>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProRefinal[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(proRefinal: IProRefinal): IProRefinal {
    const copy: IProRefinal = Object.assign({}, proRefinal, {
      ngayGioKiem: proRefinal.ngayGioKiem != null && proRefinal.ngayGioKiem.isValid() ? proRefinal.ngayGioKiem.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ngayGioKiem = res.body.ngayGioKiem != null ? moment(res.body.ngayGioKiem) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((proRefinal: IProRefinal) => {
        proRefinal.ngayGioKiem = proRefinal.ngayGioKiem != null ? moment(proRefinal.ngayGioKiem) : null;
      });
    }
    return res;
  }
}
