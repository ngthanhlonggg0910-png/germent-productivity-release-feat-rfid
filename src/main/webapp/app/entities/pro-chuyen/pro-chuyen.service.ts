import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProChuyen } from 'app/shared/model/pro-chuyen.model';

type EntityResponseType = HttpResponse<IProChuyen>;
type EntityArrayResponseType = HttpResponse<IProChuyen[]>;

@Injectable({ providedIn: 'root' })
export class ProChuyenService {
  public resourceUrl = SERVER_API_URL + 'api/pro-chuyens';

  constructor(protected http: HttpClient) {}

  create(proChuyen: IProChuyen): Observable<EntityResponseType> {
    return this.http.post<IProChuyen>(this.resourceUrl, proChuyen, { observe: 'response' });
  }

  update(proChuyen: IProChuyen): Observable<EntityResponseType> {
    return this.http.put<IProChuyen>(this.resourceUrl, proChuyen, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProChuyen>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProChuyen[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
