import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IProRefinalCC, ProRefinalCC } from 'app/shared/model/pro-refinal-cc.model';
import { ProRefinalCCService } from './pro-refinal-cc.service';

@Component({
  selector: 'jhi-pro-refinal-cc-update',
  templateUrl: './pro-refinal-cc-update.component.html'
})
export class ProRefinalCCUpdateComponent implements OnInit {
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
    nguoiKiem: []
  });

  constructor(protected proRefinalCCService: ProRefinalCCService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proRefinalCC }) => {
      this.updateForm(proRefinalCC);
    });
  }

  updateForm(proRefinalCC: IProRefinalCC) {
    this.editForm.patchValue({
      id: proRefinalCC.id,
      khxhId: proRefinalCC.khxhId,
      maHang: proRefinalCC.maHang,
      poNo: proRefinalCC.poNo,
      ngayKiemFinal: proRefinalCC.ngayKiemFinal != null ? proRefinalCC.ngayKiemFinal.format(DATE_TIME_FORMAT) : null,
      tenChuyen: proRefinalCC.tenChuyen,
      ngayGioKiem: proRefinalCC.ngayGioKiem != null ? proRefinalCC.ngayGioKiem.format(DATE_TIME_FORMAT) : null,
      soLuongKiemAql: proRefinalCC.soLuongKiemAql,
      soLuong: proRefinalCC.soLuong,
      doAm: proRefinalCC.doAm,
      tongSoLoi: proRefinalCC.tongSoLoi,
      soThuTuThung: proRefinalCC.soThuTuThung,
      doKimMd: proRefinalCC.doKimMd,
      aql: proRefinalCC.aql,
      maxLoiChoPhep: proRefinalCC.maxLoiChoPhep,
      completed: proRefinalCC.completed,
      nguoiKiem: proRefinalCC.nguoiKiem
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proRefinalCC = this.createFromForm();
    if (proRefinalCC.id !== undefined) {
      this.subscribeToSaveResponse(this.proRefinalCCService.update(proRefinalCC));
    } else {
      this.subscribeToSaveResponse(this.proRefinalCCService.create(proRefinalCC));
    }
  }

  private createFromForm(): IProRefinalCC {
    return {
      ...new ProRefinalCC(),
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
      nguoiKiem: this.editForm.get(['nguoiKiem']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProRefinalCC>>) {
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
