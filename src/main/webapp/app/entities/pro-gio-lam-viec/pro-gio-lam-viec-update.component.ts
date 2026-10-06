import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IProGioLamViec, ProGioLamViec } from 'app/shared/model/pro-gio-lam-viec.model';
import { ProGioLamViecService } from './pro-gio-lam-viec.service';

@Component({
  selector: 'jhi-pro-gio-lam-viec-update',
  templateUrl: './pro-gio-lam-viec-update.component.html'
})
export class ProGioLamViecUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    gioBatDau: [null, [Validators.required]],
    gioKetThuc: [null, [Validators.required]],
    gioLam: [],
    thoiGian: [],
    captionTV: []
  });

  constructor(protected proGioLamViecService: ProGioLamViecService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proGioLamViec }) => {
      this.updateForm(proGioLamViec);
    });
  }

  updateForm(proGioLamViec: IProGioLamViec) {
    this.editForm.patchValue({
      id: proGioLamViec.id,
      gioBatDau: proGioLamViec.gioBatDau != null ? proGioLamViec.gioBatDau.format(DATE_TIME_FORMAT) : null,
      gioKetThuc: proGioLamViec.gioKetThuc != null ? proGioLamViec.gioKetThuc.format(DATE_TIME_FORMAT) : null,
      gioLam: proGioLamViec.gioLam,
      thoiGian: proGioLamViec.thoiGian,
      captionTV: proGioLamViec.captionTV
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proGioLamViec = this.createFromForm();
    if (proGioLamViec.id !== undefined) {
      this.subscribeToSaveResponse(this.proGioLamViecService.update(proGioLamViec));
    } else {
      this.subscribeToSaveResponse(this.proGioLamViecService.create(proGioLamViec));
    }
  }

  private createFromForm(): IProGioLamViec {
    return {
      ...new ProGioLamViec(),
      id: this.editForm.get(['id']).value,
      gioBatDau:
        this.editForm.get(['gioBatDau']).value != null ? moment(this.editForm.get(['gioBatDau']).value, DATE_TIME_FORMAT) : undefined,
      gioKetThuc:
        this.editForm.get(['gioKetThuc']).value != null ? moment(this.editForm.get(['gioKetThuc']).value, DATE_TIME_FORMAT) : undefined,
      gioLam: this.editForm.get(['gioLam']).value,
      thoiGian: this.editForm.get(['thoiGian']).value,
      captionTV: this.editForm.get(['captionTV']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProGioLamViec>>) {
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
