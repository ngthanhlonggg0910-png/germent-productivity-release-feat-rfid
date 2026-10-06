import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProLoaiChuyen } from 'app/shared/model/pro-loai-chuyen.model';

type EntityResponseType = HttpResponse<IProLoaiChuyen>;
type EntityArrayResponseType = HttpResponse<IProLoaiChuyen[]>;

@Injectable({ providedIn: 'root' })
export class ProLoaiChuyenService {
  public resourceUrl = SERVER_API_URL + 'api/pro-loai-chuyens';

  constructor(protected http: HttpClient) {}

  create(proLoaiChuyen: IProLoaiChuyen): Observable<EntityResponseType> {
    return this.http.post<IProLoaiChuyen>(this.resourceUrl, proLoaiChuyen, { observe: 'response' });
  }

  update(proLoaiChuyen: IProLoaiChuyen): Observable<EntityResponseType> {
    return this.http.put<IProLoaiChuyen>(this.resourceUrl, proLoaiChuyen, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProLoaiChuyen>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProLoaiChuyen[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
