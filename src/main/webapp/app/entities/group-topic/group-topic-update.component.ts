import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IGroupTopic, GroupTopic } from 'app/shared/model/group-topic.model';
import { GroupTopicService } from './group-topic.service';

@Component({
  selector: 'jhi-group-topic-update',
  templateUrl: './group-topic-update.component.html'
})
export class GroupTopicUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [],
    topicType: [],
    type: []
  });

  constructor(protected groupTopicService: GroupTopicService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ groupTopic }) => {
      this.updateForm(groupTopic);
    });
  }

  updateForm(groupTopic: IGroupTopic) {
    this.editForm.patchValue({
      id: groupTopic.id,
      name: groupTopic.name,
      topicType: groupTopic.topicType,
      type: groupTopic.type
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const groupTopic = this.createFromForm();
    if (groupTopic.id !== undefined) {
      this.subscribeToSaveResponse(this.groupTopicService.update(groupTopic));
    } else {
      this.subscribeToSaveResponse(this.groupTopicService.create(groupTopic));
    }
  }

  private createFromForm(): IGroupTopic {
    return {
      ...new GroupTopic(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      topicType: this.editForm.get(['topicType']).value,
      type: this.editForm.get(['type']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGroupTopic>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
