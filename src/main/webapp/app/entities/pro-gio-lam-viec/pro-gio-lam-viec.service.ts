import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProGioLamViec } from 'app/shared/model/pro-gio-lam-viec.model';

type EntityResponseType = HttpResponse<IProGioLamViec>;
type EntityArrayResponseType = HttpResponse<IProGioLamViec[]>;

@Injectable({ providedIn: 'root' })
export class ProGioLamViecService {
  public resourceUrl = SERVER_API_URL + 'api/pro-gio-lam-viecs';

  constructor(protected http: HttpClient) {}

  create(proGioLamViec: IProGioLamViec): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proGioLamViec);
    return this.http
      .post<IProGioLamViec>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(proGioLamViec: IProGioLamViec): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proGioLamViec);
    return this.http
      .put<IProGioLamViec>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IProGioLamViec>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProGioLamViec[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(proGioLamViec: IProGioLamViec): IProGioLamViec {
    const copy: IProGioLamViec = Object.assign({}, proGioLamViec, {
      gioBatDau: proGioLamViec.gioBatDau != null && proGioLamViec.gioBatDau.isValid() ? proGioLamViec.gioBatDau.toJSON() : null,
      gioKetThuc: proGioLamViec.gioKetThuc != null && proGioLamViec.gioKetThuc.isValid() ? proGioLamViec.gioKetThuc.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.gioBatDau = res.body.gioBatDau != null ? moment(res.body.gioBatDau) : null;
      res.body.gioKetThuc = res.body.gioKetThuc != null ? moment(res.body.gioKetThuc) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((proGioLamViec: IProGioLamViec) => {
        proGioLamViec.gioBatDau = proGioLamViec.gioBatDau != null ? moment(proGioLamViec.gioBatDau) : null;
        proGioLamViec.gioKetThuc = proGioLamViec.gioKetThuc != null ? moment(proGioLamViec.gioKetThuc) : null;
      });
    }
    return res;
  }
}
