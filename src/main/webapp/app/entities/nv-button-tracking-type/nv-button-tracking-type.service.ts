import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INVButtonTrackingType } from 'app/shared/model/nv-button-tracking-type.model';

type EntityResponseType = HttpResponse<INVButtonTrackingType>;
type EntityArrayResponseType = HttpResponse<INVButtonTrackingType[]>;

@Injectable({ providedIn: 'root' })
export class NVButtonTrackingTypeService {
  public resourceUrl = SERVER_API_URL + 'api/nv-button-tracking-types';

  constructor(protected http: HttpClient) {}

  create(nVButtonTrackingType: INVButtonTrackingType): Observable<EntityResponseType> {
    return this.http.post<INVButtonTrackingType>(this.resourceUrl, nVButtonTrackingType, { observe: 'response' });
  }

  update(nVButtonTrackingType: INVButtonTrackingType): Observable<EntityResponseType> {
    return this.http.put<INVButtonTrackingType>(this.resourceUrl, nVButtonTrackingType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INVButtonTrackingType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INVButtonTrackingType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
