import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IProLoaiChuyen, ProLoaiChuyen } from 'app/shared/model/pro-loai-chuyen.model';
import { ProLoaiChuyenService } from './pro-loai-chuyen.service';

@Component({
  selector: 'jhi-pro-loai-chuyen-update',
  templateUrl: './pro-loai-chuyen-update.component.html'
})
export class ProLoaiChuyenUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    ten: []
  });

  constructor(protected proLoaiChuyenService: ProLoaiChuyenService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proLoaiChuyen }) => {
      this.updateForm(proLoaiChuyen);
    });
  }

  updateForm(proLoaiChuyen: IProLoaiChuyen) {
    this.editForm.patchValue({
      id: proLoaiChuyen.id,
      ten: proLoaiChuyen.ten
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proLoaiChuyen = this.createFromForm();
    if (proLoaiChuyen.id !== undefined) {
      this.subscribeToSaveResponse(this.proLoaiChuyenService.update(proLoaiChuyen));
    } else {
      this.subscribeToSaveResponse(this.proLoaiChuyenService.create(proLoaiChuyen));
    }
  }

  private createFromForm(): IProLoaiChuyen {
    return {
      ...new ProLoaiChuyen(),
      id: this.editForm.get(['id']).value,
      ten: this.editForm.get(['ten']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProLoaiChuyen>>) {
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
