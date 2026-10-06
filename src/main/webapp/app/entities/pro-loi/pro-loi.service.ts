import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProLoi } from 'app/shared/model/pro-loi.model';

type EntityResponseType = HttpResponse<IProLoi>;
type EntityArrayResponseType = HttpResponse<IProLoi[]>;

@Injectable({ providedIn: 'root' })
export class ProLoiService {
  public resourceUrl = SERVER_API_URL + 'api/pro-lois';

  constructor(protected http: HttpClient) {}

  create(proLoi: IProLoi): Observable<EntityResponseType> {
    return this.http.post<IProLoi>(this.resourceUrl, proLoi, { observe: 'response' });
  }

  update(proLoi: IProLoi): Observable<EntityResponseType> {
    return this.http.put<IProLoi>(this.resourceUrl, proLoi, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProLoi>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProLoi[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
