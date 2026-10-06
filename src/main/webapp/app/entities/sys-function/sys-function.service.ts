import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISysFunction } from 'app/shared/model/sys-function.model';

type EntityResponseType = HttpResponse<ISysFunction>;
type EntityArrayResponseType = HttpResponse<ISysFunction[]>;

@Injectable({ providedIn: 'root' })
export class SysFunctionService {
  public resourceUrl = SERVER_API_URL + 'api/sys-functions';

  constructor(protected http: HttpClient) {}

  create(sysFunction: ISysFunction): Observable<EntityResponseType> {
    return this.http.post<ISysFunction>(this.resourceUrl, sysFunction, { observe: 'response' });
  }

  update(sysFunction: ISysFunction): Observable<EntityResponseType> {
    return this.http.put<ISysFunction>(this.resourceUrl, sysFunction, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISysFunction>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISysFunction[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
