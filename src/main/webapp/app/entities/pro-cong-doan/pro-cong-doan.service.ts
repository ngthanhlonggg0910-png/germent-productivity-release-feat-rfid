import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProCongDoan } from 'app/shared/model/pro-cong-doan.model';

type EntityResponseType = HttpResponse<IProCongDoan>;
type EntityArrayResponseType = HttpResponse<IProCongDoan[]>;

@Injectable({ providedIn: 'root' })
export class ProCongDoanService {
  public resourceUrl = SERVER_API_URL + 'api/pro-cong-doans';

  constructor(protected http: HttpClient) {}

  create(proCongDoan: IProCongDoan): Observable<EntityResponseType> {
    return this.http.post<IProCongDoan>(this.resourceUrl, proCongDoan, { observe: 'response' });
  }

  update(proCongDoan: IProCongDoan): Observable<EntityResponseType> {
    return this.http.put<IProCongDoan>(this.resourceUrl, proCongDoan, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProCongDoan>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProCongDoan[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
