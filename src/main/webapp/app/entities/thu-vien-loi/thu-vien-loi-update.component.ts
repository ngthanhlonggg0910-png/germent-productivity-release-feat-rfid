import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IThuVienLoi, ThuVienLoi } from 'app/shared/model/thu-vien-loi.model';
import { ThuVienLoiService } from './thu-vien-loi.service';

@Component({
  selector: 'jhi-thu-vien-loi-update',
  templateUrl: './thu-vien-loi-update.component.html'
})
export class ThuVienLoiUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    maLoi: [],
    tenLoi: [],
    nguoiTao: [],
    ngayTao: [],
    userId: []
  });

  constructor(protected thuVienLoiService: ThuVienLoiService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ thuVienLoi }) => {
      this.updateForm(thuVienLoi);
    });
  }

  updateForm(thuVienLoi: IThuVienLoi) {
    this.editForm.patchValue({
      id: thuVienLoi.id,
      maLoi: thuVienLoi.maLoi,
      tenLoi: thuVienLoi.tenLoi,
      nguoiTao: thuVienLoi.nguoiTao,
      ngayTao: thuVienLoi.ngayTao != null ? thuVienLoi.ngayTao.format(DATE_TIME_FORMAT) : null,
      userId: thuVienLoi.userId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const thuVienLoi = this.createFromForm();
    if (thuVienLoi.id !== undefined) {
      this.subscribeToSaveResponse(this.thuVienLoiService.update(thuVienLoi));
    } else {
      this.subscribeToSaveResponse(this.thuVienLoiService.create(thuVienLoi));
    }
  }

  private createFromForm(): IThuVienLoi {
    return {
      ...new ThuVienLoi(),
      id: this.editForm.get(['id']).value,
      maLoi: this.editForm.get(['maLoi']).value,
      tenLoi: this.editForm.get(['tenLoi']).value,
      nguoiTao: this.editForm.get(['nguoiTao']).value,
      ngayTao: this.editForm.get(['ngayTao']).value != null ? moment(this.editForm.get(['ngayTao']).value, DATE_TIME_FORMAT) : undefined,
      userId: this.editForm.get(['userId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IThuVienLoi>>) {
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
