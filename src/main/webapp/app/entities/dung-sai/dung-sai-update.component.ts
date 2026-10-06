import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IDungSai, DungSai } from 'app/shared/model/dung-sai.model';
import { DungSaiService } from './dung-sai.service';

@Component({
  selector: 'jhi-dung-sai-update',
  templateUrl: './dung-sai-update.component.html'
})
export class DungSaiUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    cmLabel: [],
    inchLabel: [],
    cmValue: [],
    inchValue: []
  });

  constructor(protected dungSaiService: DungSaiService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ dungSai }) => {
      this.updateForm(dungSai);
    });
  }

  updateForm(dungSai: IDungSai) {
    this.editForm.patchValue({
      id: dungSai.id,
      cmLabel: dungSai.cmLabel,
      inchLabel: dungSai.inchLabel,
      cmValue: dungSai.cmValue,
      inchValue: dungSai.inchValue
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const dungSai = this.createFromForm();
    if (dungSai.id !== undefined) {
      this.subscribeToSaveResponse(this.dungSaiService.update(dungSai));
    } else {
      this.subscribeToSaveResponse(this.dungSaiService.create(dungSai));
    }
  }

  private createFromForm(): IDungSai {
    return {
      ...new DungSai(),
      id: this.editForm.get(['id']).value,
      cmLabel: this.editForm.get(['cmLabel']).value,
      inchLabel: this.editForm.get(['inchLabel']).value,
      cmValue: this.editForm.get(['cmValue']).value,
      inchValue: this.editForm.get(['inchValue']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDungSai>>) {
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
