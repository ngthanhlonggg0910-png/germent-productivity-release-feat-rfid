import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IKhacPhucLoi, KhacPhucLoi } from 'app/shared/model/khac-phuc-loi.model';
import { KhacPhucLoiService } from './khac-phuc-loi.service';

@Component({
  selector: 'jhi-khac-phuc-loi-update',
  templateUrl: './khac-phuc-loi-update.component.html'
})
export class KhacPhucLoiUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    maKP: [],
    tenKP: [],
    nguoiTao: [],
    ngayTao: [],
    userId: []
  });

  constructor(protected khacPhucLoiService: KhacPhucLoiService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ khacPhucLoi }) => {
      this.updateForm(khacPhucLoi);
    });
  }

  updateForm(khacPhucLoi: IKhacPhucLoi) {
    this.editForm.patchValue({
      id: khacPhucLoi.id,
      maKP: khacPhucLoi.maKP,
      tenKP: khacPhucLoi.tenKP,
      nguoiTao: khacPhucLoi.nguoiTao,
      ngayTao: khacPhucLoi.ngayTao,
      userId: khacPhucLoi.userId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const khacPhucLoi = this.createFromForm();
    if (khacPhucLoi.id !== undefined) {
      this.subscribeToSaveResponse(this.khacPhucLoiService.update(khacPhucLoi));
    } else {
      this.subscribeToSaveResponse(this.khacPhucLoiService.create(khacPhucLoi));
    }
  }

  private createFromForm(): IKhacPhucLoi {
    return {
      ...new KhacPhucLoi(),
      id: this.editForm.get(['id']).value,
      maKP: this.editForm.get(['maKP']).value,
      tenKP: this.editForm.get(['tenKP']).value,
      nguoiTao: this.editForm.get(['nguoiTao']).value,
      ngayTao: this.editForm.get(['ngayTao']).value,
      userId: this.editForm.get(['userId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IKhacPhucLoi>>) {
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
