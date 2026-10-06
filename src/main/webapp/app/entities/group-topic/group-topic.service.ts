import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGroupTopic } from 'app/shared/model/group-topic.model';

type EntityResponseType = HttpResponse<IGroupTopic>;
type EntityArrayResponseType = HttpResponse<IGroupTopic[]>;

@Injectable({ providedIn: 'root' })
export class GroupTopicService {
  public resourceUrl = SERVER_API_URL + 'api/group-topics';

  constructor(protected http: HttpClient) {}

  create(groupTopic: IGroupTopic): Observable<EntityResponseType> {
    return this.http.post<IGroupTopic>(this.resourceUrl, groupTopic, { observe: 'response' });
  }

  update(groupTopic: IGroupTopic): Observable<EntityResponseType> {
    return this.http.put<IGroupTopic>(this.resourceUrl, groupTopic, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGroupTopic>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGroupTopic[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
