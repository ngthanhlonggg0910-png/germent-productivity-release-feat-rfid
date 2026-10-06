import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProKeHoachSanXuat } from 'app/shared/model/pro-ke-hoach-san-xuat.model';

type EntityResponseType = HttpResponse<IProKeHoachSanXuat>;
type EntityArrayResponseType = HttpResponse<IProKeHoachSanXuat[]>;

@Injectable({ providedIn: 'root' })
export class ProKeHoachSanXuatService {
  public resourceUrl = SERVER_API_URL + 'api/pro-ke-hoach-san-xuats';

  constructor(protected http: HttpClient) {}

  create(proKeHoachSanXuat: IProKeHoachSanXuat): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proKeHoachSanXuat);
    return this.http
      .post<IProKeHoachSanXuat>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(proKeHoachSanXuat: IProKeHoachSanXuat): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(proKeHoachSanXuat);
    return this.http
      .put<IProKeHoachSanXuat>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IProKeHoachSanXuat>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IProKeHoachSanXuat[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(proKeHoachSanXuat: IProKeHoachSanXuat): IProKeHoachSanXuat {
    const copy: IProKeHoachSanXuat = Object.assign({}, proKeHoachSanXuat, {
      ngaySanXuat:
        proKeHoachSanXuat.ngaySanXuat != null && proKeHoachSanXuat.ngaySanXuat.isValid() ? proKeHoachSanXuat.ngaySanXuat.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ngaySanXuat = res.body.ngaySanXuat != null ? moment(res.body.ngaySanXuat) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((proKeHoachSanXuat: IProKeHoachSanXuat) => {
        proKeHoachSanXuat.ngaySanXuat = proKeHoachSanXuat.ngaySanXuat != null ? moment(proKeHoachSanXuat.ngaySanXuat) : null;
      });
    }
    return res;
  }
}
