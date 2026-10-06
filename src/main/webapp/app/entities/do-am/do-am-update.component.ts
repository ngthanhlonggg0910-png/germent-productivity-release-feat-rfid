import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IDoAm, DoAm } from 'app/shared/model/do-am.model';
import { DoAmService } from './do-am.service';

@Component({
  selector: 'jhi-do-am-update',
  templateUrl: './do-am-update.component.html'
})
export class DoAmUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    value: [],
    display: []
  });

  constructor(protected doAmService: DoAmService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ doAm }) => {
      this.updateForm(doAm);
    });
  }

  updateForm(doAm: IDoAm) {
    this.editForm.patchValue({
      id: doAm.id,
      value: doAm.value,
      display: doAm.display
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const doAm = this.createFromForm();
    if (doAm.id !== undefined) {
      this.subscribeToSaveResponse(this.doAmService.update(doAm));
    } else {
      this.subscribeToSaveResponse(this.doAmService.create(doAm));
    }
  }

  private createFromForm(): IDoAm {
    return {
      ...new DoAm(),
      id: this.editForm.get(['id']).value,
      value: this.editForm.get(['value']).value,
      display: this.editForm.get(['display']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDoAm>>) {
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
