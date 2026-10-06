import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMapControlButton } from 'app/shared/model/map-control-button.model';

type EntityResponseType = HttpResponse<IMapControlButton>;
type EntityArrayResponseType = HttpResponse<IMapControlButton[]>;

@Injectable({ providedIn: 'root' })
export class MapControlButtonService {
  public resourceUrl = SERVER_API_URL + 'api/map-control-buttons';

  constructor(protected http: HttpClient) {}

  create(mapControlButton: IMapControlButton): Observable<EntityResponseType> {
    return this.http.post<IMapControlButton>(this.resourceUrl, mapControlButton, { observe: 'response' });
  }

  update(mapControlButton: IMapControlButton): Observable<EntityResponseType> {
    return this.http.put<IMapControlButton>(this.resourceUrl, mapControlButton, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMapControlButton>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMapControlButton[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
