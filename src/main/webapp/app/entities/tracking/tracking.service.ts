import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITracking } from 'app/shared/model/tracking.model';

type EntityResponseType = HttpResponse<ITracking>;
type EntityArrayResponseType = HttpResponse<ITracking[]>;

@Injectable({ providedIn: 'root' })
export class TrackingService {
  public resourceUrl = SERVER_API_URL + 'api/trackings';

  constructor(protected http: HttpClient) {}

  create(tracking: ITracking): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tracking);
    return this.http
      .post<ITracking>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tracking: ITracking): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tracking);
    return this.http
      .put<ITracking>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITracking>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITracking[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tracking: ITracking): ITracking {
    const copy: ITracking = Object.assign({}, tracking, {
      state1Time: tracking.state1Time != null && tracking.state1Time.isValid() ? tracking.state1Time.toJSON() : null,
      state2Time: tracking.state2Time != null && tracking.state2Time.isValid() ? tracking.state2Time.toJSON() : null,
      state3Time: tracking.state3Time != null && tracking.state3Time.isValid() ? tracking.state3Time.toJSON() : null,
      state4Time: tracking.state4Time != null && tracking.state4Time.isValid() ? tracking.state4Time.toJSON() : null,
      state5Time: tracking.state5Time != null && tracking.state5Time.isValid() ? tracking.state5Time.toJSON() : null,
      state6Time: tracking.state6Time != null && tracking.state6Time.isValid() ? tracking.state6Time.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.state1Time = res.body.state1Time != null ? moment(res.body.state1Time) : null;
      res.body.state2Time = res.body.state2Time != null ? moment(res.body.state2Time) : null;
      res.body.state3Time = res.body.state3Time != null ? moment(res.body.state3Time) : null;
      res.body.state4Time = res.body.state4Time != null ? moment(res.body.state4Time) : null;
      res.body.state5Time = res.body.state5Time != null ? moment(res.body.state5Time) : null;
      res.body.state6Time = res.body.state6Time != null ? moment(res.body.state6Time) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tracking: ITracking) => {
        tracking.state1Time = tracking.state1Time != null ? moment(tracking.state1Time) : null;
        tracking.state2Time = tracking.state2Time != null ? moment(tracking.state2Time) : null;
        tracking.state3Time = tracking.state3Time != null ? moment(tracking.state3Time) : null;
        tracking.state4Time = tracking.state4Time != null ? moment(tracking.state4Time) : null;
        tracking.state5Time = tracking.state5Time != null ? moment(tracking.state5Time) : null;
        tracking.state6Time = tracking.state6Time != null ? moment(tracking.state6Time) : null;
      });
    }
    return res;
  }
}
