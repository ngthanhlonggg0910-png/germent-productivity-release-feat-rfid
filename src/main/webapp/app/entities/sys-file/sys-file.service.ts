import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISysFile } from 'app/shared/model/sys-file.model';

type EntityResponseType = HttpResponse<ISysFile>;
type EntityArrayResponseType = HttpResponse<ISysFile[]>;

@Injectable({ providedIn: 'root' })
export class SysFileService {
  public resourceUrl = SERVER_API_URL + 'api/sys-files';

  constructor(protected http: HttpClient) {}

  create(sysFile: ISysFile): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysFile);
    return this.http
      .post<ISysFile>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(sysFile: ISysFile): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sysFile);
    return this.http
      .put<ISysFile>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISysFile>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISysFile[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(sysFile: ISysFile): ISysFile {
    const copy: ISysFile = Object.assign({}, sysFile, {
      uploadedAt: sysFile.uploadedAt != null && sysFile.uploadedAt.isValid() ? sysFile.uploadedAt.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.uploadedAt = res.body.uploadedAt != null ? moment(res.body.uploadedAt) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((sysFile: ISysFile) => {
        sysFile.uploadedAt = sysFile.uploadedAt != null ? moment(sysFile.uploadedAt) : null;
      });
    }
    return res;
  }
}
