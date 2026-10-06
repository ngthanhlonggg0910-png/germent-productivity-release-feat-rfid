import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProRefinalChiTietLoi } from 'app/shared/model/pro-refinal-chi-tiet-loi.model';

type EntityResponseType = HttpResponse<IProRefinalChiTietLoi>;
type EntityArrayResponseType = HttpResponse<IProRefinalChiTietLoi[]>;

@Injectable({ providedIn: 'root' })
export class ProRefinalChiTietLoiService {
  public resourceUrl = SERVER_API_URL + 'api/pro-refinal-chi-tiet-lois';

  constructor(protected http: HttpClient) {}

  create(proRefinalChiTietLoi: IProRefinalChiTietLoi): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proRefinalChiTietLoi);
    return this.http
      .post<IProRefinalChiTietLoi>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(proRefinalChiTietLoi: IProRefinalChiTietLoi): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proRefinalChiTietLoi);
    return this.http
      .put<IProRefinalChiTietLoi>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IProRefinalChiTietLoi>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProRefinalChiTietLoi[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(proRefinalChiTietLoi: IProRefinalChiTietLoi): IProRefinalChiTietLoi {
    const copy: IProRefinalChiTietLoi = Object.assign({}, proRefinalChiTietLoi, {
      thoiGian:
        proRefinalChiTietLoi.thoiGian != null && proRefinalChiTietLoi.thoiGian.isValid() ? proRefinalChiTietLoi.thoiGian.toJSON() : null
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
      res.body.forEach((proRefinalChiTietLoi: IProRefinalChiTietLoi) => {
        proRefinalChiTietLoi.thoiGian = proRefinalChiTietLoi.thoiGian != null ? moment(proRefinalChiTietLoi.thoiGian) : null;
      });
    }
    return res;
  }
}
