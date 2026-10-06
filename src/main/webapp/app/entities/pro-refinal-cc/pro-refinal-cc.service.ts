import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProRefinalCC } from 'app/shared/model/pro-refinal-cc.model';

type EntityResponseType = HttpResponse<IProRefinalCC>;
type EntityArrayResponseType = HttpResponse<IProRefinalCC[]>;

@Injectable({ providedIn: 'root' })
export class ProRefinalCCService {
  public resourceUrl = SERVER_API_URL + 'api/pro-refinal-ccs';

  constructor(protected http: HttpClient) {}

  create(proRefinalCC: IProRefinalCC): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proRefinalCC);
    return this.http
      .post<IProRefinalCC>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(proRefinalCC: IProRefinalCC): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proRefinalCC);
    return this.http
      .put<IProRefinalCC>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IProRefinalCC>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProRefinalCC[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(proRefinalCC: IProRefinalCC): IProRefinalCC {
    const copy: IProRefinalCC = Object.assign({}, proRefinalCC, {
      ngayKiemFinal:
        proRefinalCC.ngayKiemFinal != null && proRefinalCC.ngayKiemFinal.isValid() ? proRefinalCC.ngayKiemFinal.toJSON() : null,
      ngayGioKiem: proRefinalCC.ngayGioKiem != null && proRefinalCC.ngayGioKiem.isValid() ? proRefinalCC.ngayGioKiem.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ngayKiemFinal = res.body.ngayKiemFinal != null ? moment(res.body.ngayKiemFinal) : null;
      res.body.ngayGioKiem = res.body.ngayGioKiem != null ? moment(res.body.ngayGioKiem) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((proRefinalCC: IProRefinalCC) => {
        proRefinalCC.ngayKiemFinal = proRefinalCC.ngayKiemFinal != null ? moment(proRefinalCC.ngayKiemFinal) : null;
        proRefinalCC.ngayGioKiem = proRefinalCC.ngayGioKiem != null ? moment(proRefinalCC.ngayGioKiem) : null;
      });
    }
    return res;
  }
}
