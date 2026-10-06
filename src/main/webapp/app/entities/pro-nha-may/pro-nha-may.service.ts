import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProNhaMay } from 'app/shared/model/pro-nha-may.model';

type EntityResponseType = HttpResponse<IProNhaMay>;
type EntityArrayResponseType = HttpResponse<IProNhaMay[]>;

@Injectable({ providedIn: 'root' })
export class ProNhaMayService {
  public resourceUrl = SERVER_API_URL + 'api/pro-nha-mays';

  constructor(protected http: HttpClient) {}

  create(proNhaMay: IProNhaMay): Observable<EntityResponseType> {
    return this.http.post<IProNhaMay>(this.resourceUrl, proNhaMay, { observe: 'response' });
  }

  update(proNhaMay: IProNhaMay): Observable<EntityResponseType> {
    return this.http.put<IProNhaMay>(this.resourceUrl, proNhaMay, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProNhaMay>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProNhaMay[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
