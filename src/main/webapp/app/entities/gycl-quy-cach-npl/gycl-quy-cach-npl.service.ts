import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGYCLQuyCachNPL } from 'app/shared/model/gycl-quy-cach-npl.model';

type EntityResponseType = HttpResponse<IGYCLQuyCachNPL>;
type EntityArrayResponseType = HttpResponse<IGYCLQuyCachNPL[]>;

@Injectable({ providedIn: 'root' })
export class GYCLQuyCachNPLService {
  public resourceUrl = SERVER_API_URL + 'api/gycl-quy-cach-npls';

  constructor(protected http: HttpClient) {}

  create(gYCLQuyCachNPL: IGYCLQuyCachNPL): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gYCLQuyCachNPL);
    return this.http
      .post<IGYCLQuyCachNPL>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(gYCLQuyCachNPL: IGYCLQuyCachNPL): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gYCLQuyCachNPL);
    return this.http
      .put<IGYCLQuyCachNPL>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IGYCLQuyCachNPL>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGYCLQuyCachNPL[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(gYCLQuyCachNPL: IGYCLQuyCachNPL): IGYCLQuyCachNPL {
    const copy: IGYCLQuyCachNPL = Object.assign({}, gYCLQuyCachNPL, {
      ngayTao: gYCLQuyCachNPL.ngayTao != null && gYCLQuyCachNPL.ngayTao.isValid() ? gYCLQuyCachNPL.ngayTao.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ngayTao = res.body.ngayTao != null ? moment(res.body.ngayTao) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((gYCLQuyCachNPL: IGYCLQuyCachNPL) => {
        gYCLQuyCachNPL.ngayTao = gYCLQuyCachNPL.ngayTao != null ? moment(gYCLQuyCachNPL.ngayTao) : null;
      });
    }
    return res;
  }
}
