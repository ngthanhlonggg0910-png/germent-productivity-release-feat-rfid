import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IThongSoThucThi } from 'app/shared/model/thong-so-thuc-thi.model';

type EntityResponseType = HttpResponse<IThongSoThucThi>;
type EntityArrayResponseType = HttpResponse<IThongSoThucThi[]>;

@Injectable({ providedIn: 'root' })
export class ThongSoThucThiService {
  public resourceUrl = SERVER_API_URL + 'api/thong-so-thuc-this';

  constructor(protected http: HttpClient) {}

  create(thongSoThucThi: IThongSoThucThi): Observable<EntityResponseType> {
    return this.http.post<IThongSoThucThi>(this.resourceUrl, thongSoThucThi, { observe: 'response' });
  }

  update(thongSoThucThi: IThongSoThucThi): Observable<EntityResponseType> {
    return this.http.put<IThongSoThucThi>(this.resourceUrl, thongSoThucThi, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IThongSoThucThi>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IThongSoThucThi[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
