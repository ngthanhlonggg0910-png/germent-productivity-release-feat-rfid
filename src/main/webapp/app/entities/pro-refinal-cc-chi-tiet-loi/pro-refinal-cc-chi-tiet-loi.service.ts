import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProRefinalCCChiTietLoi } from 'app/shared/model/pro-refinal-cc-chi-tiet-loi.model';

type EntityResponseType = HttpResponse<IProRefinalCCChiTietLoi>;
type EntityArrayResponseType = HttpResponse<IProRefinalCCChiTietLoi[]>;

@Injectable({ providedIn: 'root' })
export class ProRefinalCCChiTietLoiService {
  public resourceUrl = SERVER_API_URL + 'api/pro-refinal-cc-chi-tiet-lois';

  constructor(protected http: HttpClient) {}

  create(proRefinalCCChiTietLoi: IProRefinalCCChiTietLoi): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proRefinalCCChiTietLoi);
    return this.http
      .post<IProRefinalCCChiTietLoi>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(proRefinalCCChiTietLoi: IProRefinalCCChiTietLoi): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proRefinalCCChiTietLoi);
    return this.http
      .put<IProRefinalCCChiTietLoi>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IProRefinalCCChiTietLoi>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProRefinalCCChiTietLoi[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(proRefinalCCChiTietLoi: IProRefinalCCChiTietLoi): IProRefinalCCChiTietLoi {
    const copy: IProRefinalCCChiTietLoi = Object.assign({}, proRefinalCCChiTietLoi, {
      thoiGian:
        proRefinalCCChiTietLoi.thoiGian != null && proRefinalCCChiTietLoi.thoiGian.isValid()
          ? proRefinalCCChiTietLoi.thoiGian.toJSON()
          : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.thoiGian = res.body.thoiGian != null ? moment(res.body.thoiGian) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((proRefinalCCChiTietLoi: IProRefinalCCChiTietLoi) => {
        proRefinalCCChiTietLoi.thoiGian = proRefinalCCChiTietLoi.thoiGian != null ? moment(proRefinalCCChiTietLoi.thoiGian) : null;
      });
    }
    return res;
  }
}
