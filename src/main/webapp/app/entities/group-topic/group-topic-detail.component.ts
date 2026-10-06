import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGroupTopic } from 'app/shared/model/group-topic.model';

@Component({
  selector: 'jhi-group-topic-detail',
  templateUrl: './group-topic-detail.component.html'
})
export class GroupTopicDetailComponent implements OnInit {
  groupTopic: IGroupTopic;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ groupTopic }) => {
      this.groupTopic = groupTopic;
    });
  }

  previousState() {
    window.history.back();
  }
}
