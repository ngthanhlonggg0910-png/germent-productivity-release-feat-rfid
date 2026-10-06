import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProTaiKhoanCheckPoint } from 'app/shared/model/pro-tai-khoan-check-point.model';

type EntityResponseType = HttpResponse<IProTaiKhoanCheckPoint>;
type EntityArrayResponseType = HttpResponse<IProTaiKhoanCheckPoint[]>;

@Injectable({ providedIn: 'root' })
export class ProTaiKhoanCheckPointService {
  public resourceUrl = SERVER_API_URL + 'api/pro-tai-khoan-check-points';

  constructor(protected http: HttpClient) {}

  create(proTaiKhoanCheckPoint: IProTaiKhoanCheckPoint): Observable<EntityResponseType> {
    return this.http.post<IProTaiKhoanCheckPoint>(this.resourceUrl, proTaiKhoanCheckPoint, { observe: 'response' });
  }

  update(proTaiKhoanCheckPoint: IProTaiKhoanCheckPoint): Observable<EntityResponseType> {
    return this.http.put<IProTaiKhoanCheckPoint>(this.resourceUrl, proTaiKhoanCheckPoint, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProTaiKhoanCheckPoint>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProTaiKhoanCheckPoint[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
