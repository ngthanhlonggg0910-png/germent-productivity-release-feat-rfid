import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProTenMua } from 'app/shared/model/pro-ten-mua.model';

type EntityResponseType = HttpResponse<IProTenMua>;
type EntityArrayResponseType = HttpResponse<IProTenMua[]>;

@Injectable({ providedIn: 'root' })
export class ProTenMuaService {
  public resourceUrl = SERVER_API_URL + 'api/pro-ten-muas';

  constructor(protected http: HttpClient) {}

  create(proTenMua: IProTenMua): Observable<EntityResponseType> {
    return this.http.post<IProTenMua>(this.resourceUrl, proTenMua, { observe: 'response' });
  }

  update(proTenMua: IProTenMua): Observable<EntityResponseType> {
    return this.http.put<IProTenMua>(this.resourceUrl, proTenMua, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProTenMua>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProTenMua[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
