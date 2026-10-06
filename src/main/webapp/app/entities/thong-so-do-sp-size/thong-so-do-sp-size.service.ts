import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IThongSoDoSPSize } from 'app/shared/model/thong-so-do-sp-size.model';

type EntityResponseType = HttpResponse<IThongSoDoSPSize>;
type EntityArrayResponseType = HttpResponse<IThongSoDoSPSize[]>;

@Injectable({ providedIn: 'root' })
export class ThongSoDoSPSizeService {
  public resourceUrl = SERVER_API_URL + 'api/thong-so-do-sp-sizes';

  constructor(protected http: HttpClient) {}

  create(thongSoDoSPSize: IThongSoDoSPSize): Observable<EntityResponseType> {
    return this.http.post<IThongSoDoSPSize>(this.resourceUrl, thongSoDoSPSize, { observe: 'response' });
  }

  update(thongSoDoSPSize: IThongSoDoSPSize): Observable<EntityResponseType> {
    return this.http.put<IThongSoDoSPSize>(this.resourceUrl, thongSoDoSPSize, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IThongSoDoSPSize>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IThongSoDoSPSize[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
