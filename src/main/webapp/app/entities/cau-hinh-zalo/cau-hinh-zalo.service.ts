import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICauHinhZalo } from 'app/shared/model/cau-hinh-zalo.model';

type EntityResponseType = HttpResponse<ICauHinhZalo>;
type EntityArrayResponseType = HttpResponse<ICauHinhZalo[]>;

@Injectable({ providedIn: 'root' })
export class CauHinhZaloService {
  public resourceUrl = SERVER_API_URL + 'api/cau-hinh-zalos';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICauHinhZalo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICauHinhZalo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(cauHinhZalo: ICauHinhZalo): ICauHinhZalo {
    const copy: ICauHinhZalo = Object.assign({}, cauHinhZalo, {
      expireTime: cauHinhZalo.expireTime != null && cauHinhZalo.expireTime.isValid() ? cauHinhZalo.expireTime.format(DATE_FORMAT) : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.expireTime = res.body.expireTime != null ? moment(res.body.expireTime) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((cauHinhZalo: ICauHinhZalo) => {
        cauHinhZalo.expireTime = cauHinhZalo.expireTime != null ? moment(cauHinhZalo.expireTime) : null;
      });
    }
    return res;
  }
}
