import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IProChuyen, ProChuyen } from 'app/shared/model/pro-chuyen.model';
import { ProChuyenService } from './pro-chuyen.service';
import { IProLoaiChuyen } from 'app/shared/model/pro-loai-chuyen.model';
import { ProLoaiChuyenService } from 'app/entities/pro-loai-chuyen/pro-loai-chuyen.service';
import { IProNhaMay } from 'app/shared/model/pro-nha-may.model';
import { ProNhaMayService } from 'app/entities/pro-nha-may/pro-nha-may.service';

@Component({
  selector: 'jhi-pro-chuyen-update',
  templateUrl: './pro-chuyen-update.component.html'
})
export class ProChuyenUpdateComponent implements OnInit {
  isSaving: boolean;

  proloaichuyens: IProLoaiChuyen[];

  pronhamays: IProNhaMay[];

  editForm = this.fb.group({
    id: [],
    ten: [],
    stt: [],
    tenLoaiChuyen: [],
    proLoaiChuyenId: [],
    proNhaMayId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected proChuyenService: ProChuyenService,
    protected proLoaiChuyenService: ProLoaiChuyenService,
    protected proNhaMayService: ProNhaMayService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proChuyen }) => {
      this.updateForm(proChuyen);
    });
    this.proLoaiChuyenService
      .query()
      .subscribe(
        (res: HttpResponse<IProLoaiChuyen[]>) => (this.proloaichuyens = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.proNhaMayService
      .query()
      .subscribe((res: HttpResponse<IProNhaMay[]>) => (this.pronhamays = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(proChuyen: IProChuyen) {
    this.editForm.patchValue({
      id: proChuyen.id,
      ten: proChuyen.ten,
      stt: proChuyen.stt,
      tenLoaiChuyen: proChuyen.tenLoaiChuyen,
      proLoaiChuyenId: proChuyen.proLoaiChuyenId,
      proNhaMayId: proChuyen.proNhaMayId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proChuyen = this.createFromForm();
    if (proChuyen.id !== undefined) {
      this.subscribeToSaveResponse(this.proChuyenService.update(proChuyen));
    } else {
      this.subscribeToSaveResponse(this.proChuyenService.create(proChuyen));
    }
  }

  private createFromForm(): IProChuyen {
    return {
      ...new ProChuyen(),
      id: this.editForm.get(['id']).value,
      ten: this.editForm.get(['ten']).value,
      stt: this.editForm.get(['stt']).value,
      tenLoaiChuyen: this.editForm.get(['tenLoaiChuyen']).value,
      proLoaiChuyenId: this.editForm.get(['proLoaiChuyenId']).value,
      proNhaMayId: this.editForm.get(['proNhaMayId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProChuyen>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackProLoaiChuyenById(index: number, item: IProLoaiChuyen) {
    return item.id;
  }

  trackProNhaMayById(index: number, item: IProNhaMay) {
    return item.id;
  }
}
