import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGopYChatLuong } from 'app/shared/model/gop-y-chat-luong.model';

type EntityResponseType = HttpResponse<IGopYChatLuong>;
type EntityArrayResponseType = HttpResponse<IGopYChatLuong[]>;

@Injectable({ providedIn: 'root' })
export class GopYChatLuongService {
  public resourceUrl = SERVER_API_URL + 'api/gop-y-chat-luongs';

  constructor(protected http: HttpClient) {}

  create(gopYChatLuong: IGopYChatLuong): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gopYChatLuong);
    return this.http
      .post<IGopYChatLuong>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(gopYChatLuong: IGopYChatLuong): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(gopYChatLuong);
    return this.http
      .put<IGopYChatLuong>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IGopYChatLuong>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGopYChatLuong[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(gopYChatLuong: IGopYChatLuong): IGopYChatLuong {
    const copy: IGopYChatLuong = Object.assign({}, gopYChatLuong, {
      ngayBaoCao: gopYChatLuong.ngayBaoCao != null && gopYChatLuong.ngayBaoCao.isValid() ? gopYChatLuong.ngayBaoCao.toJSON() : null,
      userId: gopYChatLuong.userId != null && gopYChatLuong.userId.isValid() ? gopYChatLuong.userId.toJSON() : null,
      ngayTao: gopYChatLuong.ngayTao != null && gopYChatLuong.ngayTao.isValid() ? gopYChatLuong.ngayTao.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ngayBaoCao = res.body.ngayBaoCao != null ? moment(res.body.ngayBaoCao) : null;
      res.body.userId = res.body.userId != null ? moment(res.body.userId) : null;
      res.body.ngayTao = res.body.ngayTao != null ? moment(res.body.ngayTao) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((gopYChatLuong: IGopYChatLuong) => {
        gopYChatLuong.ngayBaoCao = gopYChatLuong.ngayBaoCao != null ? moment(gopYChatLuong.ngayBaoCao) : null;
        gopYChatLuong.userId = gopYChatLuong.userId != null ? moment(gopYChatLuong.userId) : null;
        gopYChatLuong.ngayTao = gopYChatLuong.ngayTao != null ? moment(gopYChatLuong.ngayTao) : null;
      });
    }
    return res;
  }
}
