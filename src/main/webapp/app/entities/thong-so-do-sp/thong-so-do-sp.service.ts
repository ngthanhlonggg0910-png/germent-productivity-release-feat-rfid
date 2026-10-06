import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IThongSoDoSP } from 'app/shared/model/thong-so-do-sp.model';

type EntityResponseType = HttpResponse<IThongSoDoSP>;
type EntityArrayResponseType = HttpResponse<IThongSoDoSP[]>;

@Injectable({ providedIn: 'root' })
export class ThongSoDoSPService {
  public resourceUrl = SERVER_API_URL + 'api/thong-so-do-sps';

  constructor(protected http: HttpClient) {}

  create(thongSoDoSP: IThongSoDoSP): Observable<EntityResponseType> {
    return this.http.post<IThongSoDoSP>(this.resourceUrl, thongSoDoSP, { observe: 'response' });
  }

  update(thongSoDoSP: IThongSoDoSP): Observable<EntityResponseType> {
    return this.http.put<IThongSoDoSP>(this.resourceUrl, thongSoDoSP, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IThongSoDoSP>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IThongSoDoSP[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
