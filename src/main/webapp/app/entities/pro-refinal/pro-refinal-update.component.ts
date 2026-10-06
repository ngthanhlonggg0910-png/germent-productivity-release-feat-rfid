import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IProRefinal, ProRefinal } from 'app/shared/model/pro-refinal.model';
import { ProRefinalService } from './pro-refinal.service';

@Component({
  selector: 'jhi-pro-refinal-update',
  templateUrl: './pro-refinal-update.component.html'
})
export class ProRefinalUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    proKHSXId: [],
    checkPointID: [],
    proNSCLId: [],
    ngayGioKiem: [],
    soLuongKiem: [],
    hangDatHienTai: [],
    doAm: []
  });

  constructor(protected proRefinalService: ProRefinalService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proRefinal }) => {
      this.updateForm(proRefinal);
    });
  }

  updateForm(proRefinal: IProRefinal) {
    this.editForm.patchValue({
      id: proRefinal.id,
      proKHSXId: proRefinal.proKHSXId,
      checkPointID: proRefinal.checkPointID,
      proNSCLId: proRefinal.proNSCLId,
      ngayGioKiem: proRefinal.ngayGioKiem != null ? proRefinal.ngayGioKiem.format(DATE_TIME_FORMAT) : null,
      soLuongKiem: proRefinal.soLuongKiem,
      hangDatHienTai: proRefinal.hangDatHienTai,
      doAm: proRefinal.doAm
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proRefinal = this.createFromForm();
    if (proRefinal.id !== undefined) {
      this.subscribeToSaveResponse(this.proRefinalService.update(proRefinal));
    } else {
      this.subscribeToSaveResponse(this.proRefinalService.create(proRefinal));
    }
  }

  private createFromForm(): IProRefinal {
    return {
      ...new ProRefinal(),
      id: this.editForm.get(['id']).value,
      proKHSXId: this.editForm.get(['proKHSXId']).value,
      checkPointID: this.editForm.get(['checkPointID']).value,
      proNSCLId: this.editForm.get(['proNSCLId']).value,
      ngayGioKiem:
        this.editForm.get(['ngayGioKiem']).value != null ? moment(this.editForm.get(['ngayGioKiem']).value, DATE_TIME_FORMAT) : undefined,
      soLuongKiem: this.editForm.get(['soLuongKiem']).value,
      hangDatHienTai: this.editForm.get(['hangDatHienTai']).value,
      doAm: this.editForm.get(['doAm']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProRefinal>>) {
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
