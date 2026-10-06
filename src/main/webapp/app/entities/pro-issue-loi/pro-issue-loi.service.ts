import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProIssueLoi } from 'app/shared/model/pro-issue-loi.model';

type EntityResponseType = HttpResponse<IProIssueLoi>;
type EntityArrayResponseType = HttpResponse<IProIssueLoi[]>;

@Injectable({ providedIn: 'root' })
export class ProIssueLoiService {
  public resourceUrl = SERVER_API_URL + 'api/pro-issue-lois';

  constructor(protected http: HttpClient) {}

  create(proIssueLoi: IProIssueLoi): Observable<EntityResponseType> {
    return this.http.post<IProIssueLoi>(this.resourceUrl, proIssueLoi, { observe: 'response' });
  }

  update(proIssueLoi: IProIssueLoi): Observable<EntityResponseType> {
    return this.http.put<IProIssueLoi>(this.resourceUrl, proIssueLoi, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProIssueLoi>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProIssueLoi[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
