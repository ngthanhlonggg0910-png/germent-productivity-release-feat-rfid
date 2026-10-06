import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILibSoLuongPoKiem } from 'app/shared/model/lib-so-luong-po-kiem.model';

type EntityResponseType = HttpResponse<ILibSoLuongPoKiem>;
type EntityArrayResponseType = HttpResponse<ILibSoLuongPoKiem[]>;

@Injectable({ providedIn: 'root' })
export class LibSoLuongPoKiemService {
  public resourceUrl = SERVER_API_URL + 'api/lib-so-luong-po-kiems';

  constructor(protected http: HttpClient) {}

  create(libSoLuongPoKiem: ILibSoLuongPoKiem): Observable<EntityResponseType> {
    return this.http.post<ILibSoLuongPoKiem>(this.resourceUrl, libSoLuongPoKiem, { observe: 'response' });
  }

  update(libSoLuongPoKiem: ILibSoLuongPoKiem): Observable<EntityResponseType> {
    return this.http.put<ILibSoLuongPoKiem>(this.resourceUrl, libSoLuongPoKiem, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ILibSoLuongPoKiem>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ILibSoLuongPoKiem[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
