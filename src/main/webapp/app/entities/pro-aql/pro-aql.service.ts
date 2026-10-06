import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProAql } from 'app/shared/model/pro-aql.model';

type EntityResponseType = HttpResponse<IProAql>;
type EntityArrayResponseType = HttpResponse<IProAql[]>;

@Injectable({ providedIn: 'root' })
export class ProAqlService {
  public resourceUrl = SERVER_API_URL + 'api/pro-aqls';

  constructor(protected http: HttpClient) {}

  create(proAql: IProAql): Observable<EntityResponseType> {
    return this.http.post<IProAql>(this.resourceUrl, proAql, { observe: 'response' });
  }

  update(proAql: IProAql): Observable<EntityResponseType> {
    return this.http.put<IProAql>(this.resourceUrl, proAql, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProAql>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProAql[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
