import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISysRoleHasFunction } from 'app/shared/model/sys-role-has-function.model';

type EntityResponseType = HttpResponse<ISysRoleHasFunction>;
type EntityArrayResponseType = HttpResponse<ISysRoleHasFunction[]>;

@Injectable({ providedIn: 'root' })
export class SysRoleHasFunctionService {
  public resourceUrl = SERVER_API_URL + 'api/sys-role-has-functions';

  constructor(protected http: HttpClient) {}

  create(sysRoleHasFunction: ISysRoleHasFunction): Observable<EntityResponseType> {
    return this.http.post<ISysRoleHasFunction>(this.resourceUrl, sysRoleHasFunction, { observe: 'response' });
  }

  update(sysRoleHasFunction: ISysRoleHasFunction): Observable<EntityResponseType> {
    return this.http.put<ISysRoleHasFunction>(this.resourceUrl, sysRoleHasFunction, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISysRoleHasFunction>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISysRoleHasFunction[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
