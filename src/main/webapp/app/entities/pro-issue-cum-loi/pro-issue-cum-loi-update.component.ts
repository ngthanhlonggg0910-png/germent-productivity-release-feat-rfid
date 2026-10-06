import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IProIssueCumLoi, ProIssueCumLoi } from 'app/shared/model/pro-issue-cum-loi.model';
import { ProIssueCumLoiService } from './pro-issue-cum-loi.service';

@Component({
  selector: 'jhi-pro-issue-cum-loi-update',
  templateUrl: './pro-issue-cum-loi-update.component.html'
})
export class ProIssueCumLoiUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    ten: [],
    mau: [],
    toTruong: [],
    kyThuat: [],
    qa: []
  });

  constructor(protected proIssueCumLoiService: ProIssueCumLoiService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proIssueCumLoi }) => {
      this.updateForm(proIssueCumLoi);
    });
  }

  updateForm(proIssueCumLoi: IProIssueCumLoi) {
    this.editForm.patchValue({
      id: proIssueCumLoi.id,
      ten: proIssueCumLoi.ten,
      mau: proIssueCumLoi.mau,
      toTruong: proIssueCumLoi.toTruong,
      kyThuat: proIssueCumLoi.kyThuat,
      qa: proIssueCumLoi.qa
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proIssueCumLoi = this.createFromForm();
    if (proIssueCumLoi.id !== undefined) {
      this.subscribeToSaveResponse(this.proIssueCumLoiService.update(proIssueCumLoi));
    } else {
      this.subscribeToSaveResponse(this.proIssueCumLoiService.create(proIssueCumLoi));
    }
  }

  private createFromForm(): IProIssueCumLoi {
    return {
      ...new ProIssueCumLoi(),
      id: this.editForm.get(['id']).value,
      ten: this.editForm.get(['ten']).value,
      mau: this.editForm.get(['mau']).value,
      toTruong: this.editForm.get(['toTruong']).value,
      kyThuat: this.editForm.get(['kyThuat']).value,
      qa: this.editForm.get(['qa']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProIssueCumLoi>>) {
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
