import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IDoKimMd, DoKimMd } from 'app/shared/model/do-kim-md.model';
import { DoKimMdService } from './do-kim-md.service';

@Component({
  selector: 'jhi-do-kim-md-update',
  templateUrl: './do-kim-md-update.component.html'
})
export class DoKimMdUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    value: [],
    display: []
  });

  constructor(protected doKimMdService: DoKimMdService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ doKimMd }) => {
      this.updateForm(doKimMd);
    });
  }

  updateForm(doKimMd: IDoKimMd) {
    this.editForm.patchValue({
      id: doKimMd.id,
      value: doKimMd.value,
      display: doKimMd.display
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const doKimMd = this.createFromForm();
    if (doKimMd.id !== undefined) {
      this.subscribeToSaveResponse(this.doKimMdService.update(doKimMd));
    } else {
      this.subscribeToSaveResponse(this.doKimMdService.create(doKimMd));
    }
  }

  private createFromForm(): IDoKimMd {
    return {
      ...new DoKimMd(),
      id: this.editForm.get(['id']).value,
      value: this.editForm.get(['value']).value,
      display: this.editForm.get(['display']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDoKimMd>>) {
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
