import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProCumLoi } from 'app/shared/model/pro-cum-loi.model';

type EntityResponseType = HttpResponse<IProCumLoi>;
type EntityArrayResponseType = HttpResponse<IProCumLoi[]>;

@Injectable({ providedIn: 'root' })
export class ProCumLoiService {
  public resourceUrl = SERVER_API_URL + 'api/pro-cum-lois';

  constructor(protected http: HttpClient) {}

  create(proCumLoi: IProCumLoi): Observable<EntityResponseType> {
    return this.http.post<IProCumLoi>(this.resourceUrl, proCumLoi, { observe: 'response' });
  }

  update(proCumLoi: IProCumLoi): Observable<EntityResponseType> {
    return this.http.put<IProCumLoi>(this.resourceUrl, proCumLoi, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProCumLoi>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProCumLoi[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
