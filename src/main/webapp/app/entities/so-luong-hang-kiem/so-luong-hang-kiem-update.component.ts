import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ISoLuongHangKiem, SoLuongHangKiem } from 'app/shared/model/so-luong-hang-kiem.model';
import { SoLuongHangKiemService } from './so-luong-hang-kiem.service';

@Component({
  selector: 'jhi-so-luong-hang-kiem-update',
  templateUrl: './so-luong-hang-kiem-update.component.html'
})
export class SoLuongHangKiemUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    soLuong: []
  });

  constructor(
    protected soLuongHangKiemService: SoLuongHangKiemService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ soLuongHangKiem }) => {
      this.updateForm(soLuongHangKiem);
    });
  }

  updateForm(soLuongHangKiem: ISoLuongHangKiem) {
    this.editForm.patchValue({
      id: soLuongHangKiem.id,
      soLuong: soLuongHangKiem.soLuong
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const soLuongHangKiem = this.createFromForm();
    if (soLuongHangKiem.id !== undefined) {
      this.subscribeToSaveResponse(this.soLuongHangKiemService.update(soLuongHangKiem));
    } else {
      this.subscribeToSaveResponse(this.soLuongHangKiemService.create(soLuongHangKiem));
    }
  }

  private createFromForm(): ISoLuongHangKiem {
    return {
      ...new SoLuongHangKiem(),
      id: this.editForm.get(['id']).value,
      soLuong: this.editForm.get(['soLuong']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISoLuongHangKiem>>) {
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
