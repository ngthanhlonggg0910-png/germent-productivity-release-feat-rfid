import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IKhacPhucLoi } from 'app/shared/model/khac-phuc-loi.model';

type EntityResponseType = HttpResponse<IKhacPhucLoi>;
type EntityArrayResponseType = HttpResponse<IKhacPhucLoi[]>;

@Injectable({ providedIn: 'root' })
export class KhacPhucLoiService {
  public resourceUrl = SERVER_API_URL + 'api/khac-phuc-lois';

  constructor(protected http: HttpClient) {}

  create(khacPhucLoi: IKhacPhucLoi): Observable<EntityResponseType> {
    return this.http.post<IKhacPhucLoi>(this.resourceUrl, khacPhucLoi, { observe: 'response' });
  }

  update(khacPhucLoi: IKhacPhucLoi): Observable<EntityResponseType> {
    return this.http.put<IKhacPhucLoi>(this.resourceUrl, khacPhucLoi, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IKhacPhucLoi>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IKhacPhucLoi[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
