import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProNSCLChiTietLoi } from 'app/shared/model/pro-nscl-chi-tiet-loi.model';

type EntityResponseType = HttpResponse<IProNSCLChiTietLoi>;
type EntityArrayResponseType = HttpResponse<IProNSCLChiTietLoi[]>;

@Injectable({ providedIn: 'root' })
export class ProNSCLChiTietLoiService {
  public resourceUrl = SERVER_API_URL + 'api/pro-nscl-chi-tiet-lois';

  constructor(protected http: HttpClient) {}

  create(proNSCLChiTietLoi: IProNSCLChiTietLoi): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proNSCLChiTietLoi);
    return this.http
      .post<IProNSCLChiTietLoi>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(proNSCLChiTietLoi: IProNSCLChiTietLoi): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proNSCLChiTietLoi);
    return this.http
      .put<IProNSCLChiTietLoi>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IProNSCLChiTietLoi>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProNSCLChiTietLoi[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(proNSCLChiTietLoi: IProNSCLChiTietLoi): IProNSCLChiTietLoi {
    const copy: IProNSCLChiTietLoi = Object.assign({}, proNSCLChiTietLoi, {
      thoiGian: proNSCLChiTietLoi.thoiGian != null && proNSCLChiTietLoi.thoiGian.isValid() ? proNSCLChiTietLoi.thoiGian.toJSON() : null
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
      res.body.forEach((proNSCLChiTietLoi: IProNSCLChiTietLoi) => {
        proNSCLChiTietLoi.thoiGian = proNSCLChiTietLoi.thoiGian != null ? moment(proNSCLChiTietLoi.thoiGian) : null;
      });
    }
    return res;
  }
}
