import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProNSCL } from 'app/shared/model/pro-nscl.model';

type EntityResponseType = HttpResponse<IProNSCL>;
type EntityArrayResponseType = HttpResponse<IProNSCL[]>;

@Injectable({ providedIn: 'root' })
export class ProNSCLService {
  public resourceUrl = SERVER_API_URL + 'api/pro-nscls';

  constructor(protected http: HttpClient) {}

  create(proNSCL: IProNSCL): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proNSCL);
    return this.http
      .post<IProNSCL>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(proNSCL: IProNSCL): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proNSCL);
    return this.http
      .put<IProNSCL>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IProNSCL>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProNSCL[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(proNSCL: IProNSCL): IProNSCL {
    const copy: IProNSCL = Object.assign({}, proNSCL, {
      ngaySanXuat: proNSCL.ngaySanXuat && proNSCL.ngaySanXuat.isValid() ? proNSCL.ngaySanXuat.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ngaySanXuat = res.body.ngaySanXuat ? moment(res.body.ngaySanXuat) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((proNSCL: IProNSCL) => {
        proNSCL.ngaySanXuat = proNSCL.ngaySanXuat ? moment(proNSCL.ngaySanXuat) : undefined;
      });
    }
    return res;
  }
}
