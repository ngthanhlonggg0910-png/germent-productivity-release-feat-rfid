import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IProFinal, ProFinal } from 'app/shared/model/pro-final.model';
import { ProFinalService } from './pro-final.service';

@Component({
  selector: 'jhi-pro-final-update',
  templateUrl: './pro-final-update.component.html'
})
export class ProFinalUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    khxhId: [],
    maHang: [],
    poNo: [],
    ngayKiemFinal: [],
    tenChuyen: [],
    ngayGioKiem: [],
    soLuongKiemAql: [],
    soLuong: [],
    doAm: [],
    tongSoLoi: [],
    soThuTuThung: [],
    doKimMd: [],
    aql: [],
    maxLoiChoPhep: [],
    completed: [],
    soLuongPoKiem: [],
    pass: [],
    nguoiKiem: []
  });

  constructor(protected proFinalService: ProFinalService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proFinal }) => {
      this.updateForm(proFinal);
    });
  }

  updateForm(proFinal: IProFinal) {
    this.editForm.patchValue({
      id: proFinal.id,
      khxhId: proFinal.khxhId,
      maHang: proFinal.maHang,
      poNo: proFinal.poNo,
      ngayKiemFinal: proFinal.ngayKiemFinal != null ? proFinal.ngayKiemFinal.format(DATE_TIME_FORMAT) : null,
      tenChuyen: proFinal.tenChuyen,
      ngayGioKiem: proFinal.ngayGioKiem != null ? proFinal.ngayGioKiem.format(DATE_TIME_FORMAT) : null,
      soLuongKiemAql: proFinal.soLuongKiemAql,
      soLuong: proFinal.soLuong,
      doAm: proFinal.doAm,
      tongSoLoi: proFinal.tongSoLoi,
      soThuTuThung: proFinal.soThuTuThung,
      doKimMd: proFinal.doKimMd,
      aql: proFinal.aql,
      maxLoiChoPhep: proFinal.maxLoiChoPhep,
      completed: proFinal.completed,
      soLuongPoKiem: proFinal.soLuongPoKiem,
      pass: proFinal.pass,
      nguoiKiem: proFinal.nguoiKiem
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proFinal = this.createFromForm();
    if (proFinal.id !== undefined) {
      this.subscribeToSaveResponse(this.proFinalService.update(proFinal));
    } else {
      this.subscribeToSaveResponse(this.proFinalService.create(proFinal));
    }
  }

  private createFromForm(): IProFinal {
    return {
      ...new ProFinal(),
      id: this.editForm.get(['id']).value,
      khxhId: this.editForm.get(['khxhId']).value,
      maHang: this.editForm.get(['maHang']).value,
      poNo: this.editForm.get(['poNo']).value,
      ngayKiemFinal:
        this.editForm.get(['ngayKiemFinal']).value != null
          ? moment(this.editForm.get(['ngayKiemFinal']).value, DATE_TIME_FORMAT)
          : undefined,
      tenChuyen: this.editForm.get(['tenChuyen']).value,
      ngayGioKiem:
        this.editForm.get(['ngayGioKiem']).value != null ? moment(this.editForm.get(['ngayGioKiem']).value, DATE_TIME_FORMAT) : undefined,
      soLuongKiemAql: this.editForm.get(['soLuongKiemAql']).value,
      soLuong: this.editForm.get(['soLuong']).value,
      doAm: this.editForm.get(['doAm']).value,
      tongSoLoi: this.editForm.get(['tongSoLoi']).value,
      soThuTuThung: this.editForm.get(['soThuTuThung']).value,
      doKimMd: this.editForm.get(['doKimMd']).value,
      aql: this.editForm.get(['aql']).value,
      maxLoiChoPhep: this.editForm.get(['maxLoiChoPhep']).value,
      completed: this.editForm.get(['completed']).value,
      soLuongPoKiem: this.editForm.get(['soLuongPoKiem']).value,
      pass: this.editForm.get(['pass']).value,
      nguoiKiem: this.editForm.get(['nguoiKiem']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProFinal>>) {
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
