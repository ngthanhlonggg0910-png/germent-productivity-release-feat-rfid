import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IProCumLoi, ProCumLoi } from 'app/shared/model/pro-cum-loi.model';
import { ProCumLoiService } from './pro-cum-loi.service';

@Component({
  selector: 'jhi-pro-cum-loi-update',
  templateUrl: './pro-cum-loi-update.component.html'
})
export class ProCumLoiUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    ten: [],
    mau: []
  });

  constructor(protected proCumLoiService: ProCumLoiService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proCumLoi }) => {
      this.updateForm(proCumLoi);
    });
  }

  updateForm(proCumLoi: IProCumLoi) {
    this.editForm.patchValue({
      id: proCumLoi.id,
      ten: proCumLoi.ten,
      mau: proCumLoi.mau
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proCumLoi = this.createFromForm();
    if (proCumLoi.id !== undefined) {
      this.subscribeToSaveResponse(this.proCumLoiService.update(proCumLoi));
    } else {
      this.subscribeToSaveResponse(this.proCumLoiService.create(proCumLoi));
    }
  }

  private createFromForm(): IProCumLoi {
    return {
      ...new ProCumLoi(),
      id: this.editForm.get(['id']).value,
      ten: this.editForm.get(['ten']).value,
      mau: this.editForm.get(['mau']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProCumLoi>>) {
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
