import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProFinal } from 'app/shared/model/pro-final.model';

type EntityResponseType = HttpResponse<IProFinal>;
type EntityArrayResponseType = HttpResponse<IProFinal[]>;

@Injectable({ providedIn: 'root' })
export class ProFinalService {
  public resourceUrl = SERVER_API_URL + 'api/pro-finals';

  constructor(protected http: HttpClient) {}

  create(proFinal: IProFinal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proFinal);
    return this.http
      .post<IProFinal>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(proFinal: IProFinal): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proFinal);
    return this.http
      .put<IProFinal>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IProFinal>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProFinal[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(proFinal: IProFinal): IProFinal {
    const copy: IProFinal = Object.assign({}, proFinal, {
      ngayKiemFinal: proFinal.ngayKiemFinal != null && proFinal.ngayKiemFinal.isValid() ? proFinal.ngayKiemFinal.toJSON() : null,
      ngayGioKiem: proFinal.ngayGioKiem != null && proFinal.ngayGioKiem.isValid() ? proFinal.ngayGioKiem.toJSON() : null
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
      res.body.forEach((proFinal: IProFinal) => {
        proFinal.ngayKiemFinal = proFinal.ngayKiemFinal != null ? moment(proFinal.ngayKiemFinal) : null;
        proFinal.ngayGioKiem = proFinal.ngayGioKiem != null ? moment(proFinal.ngayGioKiem) : null;
      });
    }
    return res;
  }
}
