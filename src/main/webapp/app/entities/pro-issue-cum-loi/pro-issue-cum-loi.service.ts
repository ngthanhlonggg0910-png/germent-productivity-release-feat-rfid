import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProIssueCumLoi } from 'app/shared/model/pro-issue-cum-loi.model';

type EntityResponseType = HttpResponse<IProIssueCumLoi>;
type EntityArrayResponseType = HttpResponse<IProIssueCumLoi[]>;

@Injectable({ providedIn: 'root' })
export class ProIssueCumLoiService {
  public resourceUrl = SERVER_API_URL + 'api/pro-issue-cum-lois';

  constructor(protected http: HttpClient) {}

  create(proIssueCumLoi: IProIssueCumLoi): Observable<EntityResponseType> {
    return this.http.post<IProIssueCumLoi>(this.resourceUrl, proIssueCumLoi, { observe: 'response' });
  }

  update(proIssueCumLoi: IProIssueCumLoi): Observable<EntityResponseType> {
    return this.http.put<IProIssueCumLoi>(this.resourceUrl, proIssueCumLoi, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProIssueCumLoi>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProIssueCumLoi[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
