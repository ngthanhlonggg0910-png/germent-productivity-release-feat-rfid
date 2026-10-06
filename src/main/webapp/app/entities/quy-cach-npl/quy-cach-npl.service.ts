import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IQuyCachNPL } from 'app/shared/model/quy-cach-npl.model';

type EntityResponseType = HttpResponse<IQuyCachNPL>;
type EntityArrayResponseType = HttpResponse<IQuyCachNPL[]>;

@Injectable({ providedIn: 'root' })
export class QuyCachNPLService {
  public resourceUrl = SERVER_API_URL + 'api/quy-cach-npls';

  constructor(protected http: HttpClient) {}

  create(quyCachNPL: IQuyCachNPL): Observable<EntityResponseType> {
    return this.http.post<IQuyCachNPL>(this.resourceUrl, quyCachNPL, { observe: 'response' });
  }

  update(quyCachNPL: IQuyCachNPL): Observable<EntityResponseType> {
    return this.http.put<IQuyCachNPL>(this.resourceUrl, quyCachNPL, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IQuyCachNPL>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IQuyCachNPL[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
