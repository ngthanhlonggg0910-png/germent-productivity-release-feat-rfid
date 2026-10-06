import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDungSai } from 'app/shared/model/dung-sai.model';

type EntityResponseType = HttpResponse<IDungSai>;
type EntityArrayResponseType = HttpResponse<IDungSai[]>;

@Injectable({ providedIn: 'root' })
export class DungSaiService {
  public resourceUrl = SERVER_API_URL + 'api/dung-sais';

  constructor(protected http: HttpClient) {}

  create(dungSai: IDungSai): Observable<EntityResponseType> {
    return this.http.post<IDungSai>(this.resourceUrl, dungSai, { observe: 'response' });
  }

  update(dungSai: IDungSai): Observable<EntityResponseType> {
    return this.http.put<IDungSai>(this.resourceUrl, dungSai, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDungSai>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDungSai[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
