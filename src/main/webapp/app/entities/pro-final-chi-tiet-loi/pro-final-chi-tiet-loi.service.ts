import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProFinalChiTietLoi } from 'app/shared/model/pro-final-chi-tiet-loi.model';

type EntityResponseType = HttpResponse<IProFinalChiTietLoi>;
type EntityArrayResponseType = HttpResponse<IProFinalChiTietLoi[]>;

@Injectable({ providedIn: 'root' })
export class ProFinalChiTietLoiService {
  public resourceUrl = SERVER_API_URL + 'api/pro-final-chi-tiet-lois';

  constructor(protected http: HttpClient) {}

  create(proFinalChiTietLoi: IProFinalChiTietLoi): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proFinalChiTietLoi);
    return this.http
      .post<IProFinalChiTietLoi>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(proFinalChiTietLoi: IProFinalChiTietLoi): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proFinalChiTietLoi);
    return this.http
      .put<IProFinalChiTietLoi>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IProFinalChiTietLoi>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProFinalChiTietLoi[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(proFinalChiTietLoi: IProFinalChiTietLoi): IProFinalChiTietLoi {
    const copy: IProFinalChiTietLoi = Object.assign({}, proFinalChiTietLoi, {
      thoiGian: proFinalChiTietLoi.thoiGian != null && proFinalChiTietLoi.thoiGian.isValid() ? proFinalChiTietLoi.thoiGian.toJSON() : null
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
      res.body.forEach((proFinalChiTietLoi: IProFinalChiTietLoi) => {
        proFinalChiTietLoi.thoiGian = proFinalChiTietLoi.thoiGian != null ? moment(proFinalChiTietLoi.thoiGian) : null;
      });
    }
    return res;
  }
}
