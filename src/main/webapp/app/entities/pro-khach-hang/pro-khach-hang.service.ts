import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProKhachHang } from 'app/shared/model/pro-khach-hang.model';

type EntityResponseType = HttpResponse<IProKhachHang>;
type EntityArrayResponseType = HttpResponse<IProKhachHang[]>;

@Injectable({ providedIn: 'root' })
export class ProKhachHangService {
  public resourceUrl = SERVER_API_URL + 'api/pro-khach-hangs';

  constructor(protected http: HttpClient) {}

  create(proKhachHang: IProKhachHang): Observable<EntityResponseType> {
    return this.http.post<IProKhachHang>(this.resourceUrl, proKhachHang, { observe: 'response' });
  }

  update(proKhachHang: IProKhachHang): Observable<EntityResponseType> {
    return this.http.put<IProKhachHang>(this.resourceUrl, proKhachHang, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProKhachHang>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProKhachHang[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
