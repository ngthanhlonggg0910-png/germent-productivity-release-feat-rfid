import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INVButton } from 'app/shared/model/nv-button.model';

type EntityResponseType = HttpResponse<INVButton>;
type EntityArrayResponseType = HttpResponse<INVButton[]>;

@Injectable({ providedIn: 'root' })
export class NVButtonService {
  public resourceUrl = SERVER_API_URL + 'api/nv-buttons';

  constructor(protected http: HttpClient) {}

  create(nVButton: INVButton): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(nVButton);
    return this.http
      .post<INVButton>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(nVButton: INVButton): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(nVButton);
    return this.http
      .put<INVButton>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<INVButton>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<INVButton[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(nVButton: INVButton): INVButton {
    const copy: INVButton = Object.assign({}, nVButton, {
      lastUpdateState: nVButton.lastUpdateState != null && nVButton.lastUpdateState.isValid() ? nVButton.lastUpdateState.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.lastUpdateState = res.body.lastUpdateState != null ? moment(res.body.lastUpdateState) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((nVButton: INVButton) => {
        nVButton.lastUpdateState = nVButton.lastUpdateState != null ? moment(nVButton.lastUpdateState) : null;
      });
    }
    return res;
  }
}
