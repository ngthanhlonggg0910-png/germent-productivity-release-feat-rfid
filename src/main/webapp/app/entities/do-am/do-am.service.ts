import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDoAm } from 'app/shared/model/do-am.model';

type EntityResponseType = HttpResponse<IDoAm>;
type EntityArrayResponseType = HttpResponse<IDoAm[]>;

@Injectable({ providedIn: 'root' })
export class DoAmService {
  public resourceUrl = SERVER_API_URL + 'api/do-ams';

  constructor(protected http: HttpClient) {}

  create(doAm: IDoAm): Observable<EntityResponseType> {
    return this.http.post<IDoAm>(this.resourceUrl, doAm, { observe: 'response' });
  }

  update(doAm: IDoAm): Observable<EntityResponseType> {
    return this.http.put<IDoAm>(this.resourceUrl, doAm, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDoAm>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDoAm[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
