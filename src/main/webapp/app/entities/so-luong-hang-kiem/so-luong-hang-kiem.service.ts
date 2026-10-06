import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISoLuongHangKiem } from 'app/shared/model/so-luong-hang-kiem.model';

type EntityResponseType = HttpResponse<ISoLuongHangKiem>;
type EntityArrayResponseType = HttpResponse<ISoLuongHangKiem[]>;

@Injectable({ providedIn: 'root' })
export class SoLuongHangKiemService {
  public resourceUrl = SERVER_API_URL + 'api/so-luong-hang-kiems';

  constructor(protected http: HttpClient) {}

  create(soLuongHangKiem: ISoLuongHangKiem): Observable<EntityResponseType> {
    return this.http.post<ISoLuongHangKiem>(this.resourceUrl, soLuongHangKiem, { observe: 'response' });
  }

  update(soLuongHangKiem: ISoLuongHangKiem): Observable<EntityResponseType> {
    return this.http.put<ISoLuongHangKiem>(this.resourceUrl, soLuongHangKiem, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISoLuongHangKiem>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISoLuongHangKiem[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
