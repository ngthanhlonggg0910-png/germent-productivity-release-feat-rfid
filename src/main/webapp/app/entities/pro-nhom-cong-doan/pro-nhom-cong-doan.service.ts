import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProNhomCongDoan } from 'app/shared/model/pro-nhom-cong-doan.model';

type EntityResponseType = HttpResponse<IProNhomCongDoan>;
type EntityArrayResponseType = HttpResponse<IProNhomCongDoan[]>;

@Injectable({ providedIn: 'root' })
export class ProNhomCongDoanService {
  public resourceUrl = SERVER_API_URL + 'api/pro-nhom-cong-doans';

  constructor(protected http: HttpClient) {}

  create(proNhomCongDoan: IProNhomCongDoan): Observable<EntityResponseType> {
    return this.http.post<IProNhomCongDoan>(this.resourceUrl, proNhomCongDoan, { observe: 'response' });
  }

  update(proNhomCongDoan: IProNhomCongDoan): Observable<EntityResponseType> {
    return this.http.put<IProNhomCongDoan>(this.resourceUrl, proNhomCongDoan, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProNhomCongDoan>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProNhomCongDoan[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
