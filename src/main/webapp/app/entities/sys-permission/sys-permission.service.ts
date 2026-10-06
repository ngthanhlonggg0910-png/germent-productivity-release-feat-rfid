import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISysPermission } from 'app/shared/model/sys-permission.model';

type EntityResponseType = HttpResponse<ISysPermission>;
type EntityArrayResponseType = HttpResponse<ISysPermission[]>;

@Injectable({ providedIn: 'root' })
export class SysPermissionService {
  public resourceUrl = SERVER_API_URL + 'api/sys-permissions';

  constructor(protected http: HttpClient) {}

  create(sysPermission: ISysPermission): Observable<EntityResponseType> {
    return this.http.post<ISysPermission>(this.resourceUrl, sysPermission, { observe: 'response' });
  }

  update(sysPermission: ISysPermission): Observable<EntityResponseType> {
    return this.http.put<ISysPermission>(this.resourceUrl, sysPermission, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISysPermission>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISysPermission[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
