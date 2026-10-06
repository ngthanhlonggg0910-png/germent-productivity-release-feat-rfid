import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IProIssue, ProIssue } from 'app/shared/model/pro-issue.model';
import { ProIssueService } from './pro-issue.service';

@Component({
  selector: 'jhi-pro-issue-update',
  templateUrl: './pro-issue-update.component.html'
})
export class ProIssueUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    maHang: [],
    pono: [],
    tenChuyen: [],
    ngayGioBatDau: [],
    ngayGioKetThuc: [],
    maLoi: [],
    moTaLoi: [],
    maMay: [],
    soTram: [],
    proChuyenId: [],
    proSanPhamId: [],
    nguoiTao: [],
    checkPointType: [],
    checkPointId: [],
    checkPointName: []
  });

  constructor(protected proIssueService: ProIssueService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proIssue }) => {
      this.updateForm(proIssue);
    });
  }

  updateForm(proIssue: IProIssue) {
    this.editForm.patchValue({
      id: proIssue.id,
      maHang: proIssue.maHang,
      pono: proIssue.pono,
      tenChuyen: proIssue.tenChuyen,
      ngayGioBatDau: proIssue.ngayGioBatDau != null ? proIssue.ngayGioBatDau.format(DATE_TIME_FORMAT) : null,
      ngayGioKetThuc: proIssue.ngayGioKetThuc != null ? proIssue.ngayGioKetThuc.format(DATE_TIME_FORMAT) : null,
      maLoi: proIssue.maLoi,
      moTaLoi: proIssue.moTaLoi,
      maMay: proIssue.maMay,
      soTram: proIssue.soTram,
      proChuyenId: proIssue.proChuyenId,
      proSanPhamId: proIssue.proSanPhamId,
      nguoiTao: proIssue.nguoiTao,
      checkPointType: proIssue.checkPointType,
      checkPointId: proIssue.checkPointId,
      checkPointName: proIssue.checkPointName
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proIssue = this.createFromForm();
    if (proIssue.id !== undefined) {
      this.subscribeToSaveResponse(this.proIssueService.update(proIssue));
    } else {
      this.subscribeToSaveResponse(this.proIssueService.create(proIssue));
    }
  }

  private createFromForm(): IProIssue {
    return {
      ...new ProIssue(),
      id: this.editForm.get(['id']).value,
      maHang: this.editForm.get(['maHang']).value,
      pono: this.editForm.get(['pono']).value,
      tenChuyen: this.editForm.get(['tenChuyen']).value,
      ngayGioBatDau:
        this.editForm.get(['ngayGioBatDau']).value != null
          ? moment(this.editForm.get(['ngayGioBatDau']).value, DATE_TIME_FORMAT)
          : undefined,
      ngayGioKetThuc:
        this.editForm.get(['ngayGioKetThuc']).value != null
          ? moment(this.editForm.get(['ngayGioKetThuc']).value, DATE_TIME_FORMAT)
          : undefined,
      maLoi: this.editForm.get(['maLoi']).value,
      moTaLoi: this.editForm.get(['moTaLoi']).value,
      maMay: this.editForm.get(['maMay']).value,
      soTram: this.editForm.get(['soTram']).value,
      proChuyenId: this.editForm.get(['proChuyenId']).value,
      proSanPhamId: this.editForm.get(['proSanPhamId']).value,
      nguoiTao: this.editForm.get(['nguoiTao']).value,
      checkPointType: this.editForm.get(['checkPointType']).value,
      checkPointId: this.editForm.get(['checkPointId']).value,
      checkPointName: this.editForm.get(['checkPointName']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProIssue>>) {
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
