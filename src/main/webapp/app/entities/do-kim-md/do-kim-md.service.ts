import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDoKimMd } from 'app/shared/model/do-kim-md.model';

type EntityResponseType = HttpResponse<IDoKimMd>;
type EntityArrayResponseType = HttpResponse<IDoKimMd[]>;

@Injectable({ providedIn: 'root' })
export class DoKimMdService {
  public resourceUrl = SERVER_API_URL + 'api/do-kim-mds';

  constructor(protected http: HttpClient) {}

  create(doKimMd: IDoKimMd): Observable<EntityResponseType> {
    return this.http.post<IDoKimMd>(this.resourceUrl, doKimMd, { observe: 'response' });
  }

  update(doKimMd: IDoKimMd): Observable<EntityResponseType> {
    return this.http.put<IDoKimMd>(this.resourceUrl, doKimMd, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDoKimMd>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDoKimMd[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
