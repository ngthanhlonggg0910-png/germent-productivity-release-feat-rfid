import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISysGroupModule } from 'app/shared/model/sys-group-module.model';

type EntityResponseType = HttpResponse<ISysGroupModule>;
type EntityArrayResponseType = HttpResponse<ISysGroupModule[]>;

@Injectable({ providedIn: 'root' })
export class SysGroupModuleService {
  public resourceUrl = SERVER_API_URL + 'api/sys-group-modules';

  constructor(protected http: HttpClient) {}

  create(sysGroupModule: ISysGroupModule): Observable<EntityResponseType> {
    return this.http.post<ISysGroupModule>(this.resourceUrl, sysGroupModule, { observe: 'response' });
  }

  update(sysGroupModule: ISysGroupModule): Observable<EntityResponseType> {
    return this.http.put<ISysGroupModule>(this.resourceUrl, sysGroupModule, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISysGroupModule>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISysGroupModule[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
