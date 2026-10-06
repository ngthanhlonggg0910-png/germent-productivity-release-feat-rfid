import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IThongSoDo } from 'app/shared/model/thong-so-do.model';

type EntityResponseType = HttpResponse<IThongSoDo>;
type EntityArrayResponseType = HttpResponse<IThongSoDo[]>;

@Injectable({ providedIn: 'root' })
export class ThongSoDoService {
  public resourceUrl = SERVER_API_URL + 'api/thong-so-dos';

  constructor(protected http: HttpClient) {}

  create(thongSoDo: IThongSoDo): Observable<EntityResponseType> {
    return this.http.post<IThongSoDo>(this.resourceUrl, thongSoDo, { observe: 'response' });
  }

  update(thongSoDo: IThongSoDo): Observable<EntityResponseType> {
    return this.http.put<IThongSoDo>(this.resourceUrl, thongSoDo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IThongSoDo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IThongSoDo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
