import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IThuVienLoi } from 'app/shared/model/thu-vien-loi.model';

type EntityResponseType = HttpResponse<IThuVienLoi>;
type EntityArrayResponseType = HttpResponse<IThuVienLoi[]>;

@Injectable({ providedIn: 'root' })
export class ThuVienLoiService {
  public resourceUrl = SERVER_API_URL + 'api/thu-vien-lois';

  constructor(protected http: HttpClient) {}

  create(thuVienLoi: IThuVienLoi): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(thuVienLoi);
    return this.http
      .post<IThuVienLoi>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(thuVienLoi: IThuVienLoi): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(thuVienLoi);
    return this.http
      .put<IThuVienLoi>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IThuVienLoi>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IThuVienLoi[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(thuVienLoi: IThuVienLoi): IThuVienLoi {
    const copy: IThuVienLoi = Object.assign({}, thuVienLoi, {
      ngayTao: thuVienLoi.ngayTao != null && thuVienLoi.ngayTao.isValid() ? thuVienLoi.ngayTao.toJSON() : null
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
      res.body.forEach((thuVienLoi: IThuVienLoi) => {
        thuVienLoi.ngayTao = thuVienLoi.ngayTao != null ? moment(thuVienLoi.ngayTao) : null;
      });
    }
    return res;
  }
}
