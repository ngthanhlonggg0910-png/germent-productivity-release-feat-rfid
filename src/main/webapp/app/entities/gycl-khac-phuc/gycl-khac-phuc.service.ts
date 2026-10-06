import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGYCLKhacPhuc } from 'app/shared/model/gycl-khac-phuc.model';

type EntityResponseType = HttpResponse<IGYCLKhacPhuc>;
type EntityArrayResponseType = HttpResponse<IGYCLKhacPhuc[]>;

@Injectable({ providedIn: 'root' })
export class GYCLKhacPhucService {
  public resourceUrl = SERVER_API_URL + 'api/gycl-khac-phucs';

  constructor(protected http: HttpClient) {}

  create(gYCLKhacPhuc: IGYCLKhacPhuc): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gYCLKhacPhuc);
    return this.http
      .post<IGYCLKhacPhuc>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(gYCLKhacPhuc: IGYCLKhacPhuc): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gYCLKhacPhuc);
    return this.http
      .put<IGYCLKhacPhuc>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IGYCLKhacPhuc>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGYCLKhacPhuc[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(gYCLKhacPhuc: IGYCLKhacPhuc): IGYCLKhacPhuc {
    const copy: IGYCLKhacPhuc = Object.assign({}, gYCLKhacPhuc, {
      userId: gYCLKhacPhuc.userId != null && gYCLKhacPhuc.userId.isValid() ? gYCLKhacPhuc.userId.toJSON() : null,
      ngayTao: gYCLKhacPhuc.ngayTao != null && gYCLKhacPhuc.ngayTao.isValid() ? gYCLKhacPhuc.ngayTao.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.userId = res.body.userId != null ? moment(res.body.userId) : null;
      res.body.ngayTao = res.body.ngayTao != null ? moment(res.body.ngayTao) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((gYCLKhacPhuc: IGYCLKhacPhuc) => {
        gYCLKhacPhuc.userId = gYCLKhacPhuc.userId != null ? moment(gYCLKhacPhuc.userId) : null;
        gYCLKhacPhuc.ngayTao = gYCLKhacPhuc.ngayTao != null ? moment(gYCLKhacPhuc.ngayTao) : null;
      });
    }
    return res;
  }
}
