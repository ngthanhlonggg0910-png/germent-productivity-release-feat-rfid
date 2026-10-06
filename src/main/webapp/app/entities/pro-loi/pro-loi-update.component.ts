import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IProLoi, ProLoi } from 'app/shared/model/pro-loi.model';
import { ProLoiService } from './pro-loi.service';
import { IProCumLoi } from 'app/shared/model/pro-cum-loi.model';
import { ProCumLoiService } from 'app/entities/pro-cum-loi/pro-cum-loi.service';

@Component({
  selector: 'jhi-pro-loi-update',
  templateUrl: './pro-loi-update.component.html'
})
export class ProLoiUpdateComponent implements OnInit {
  isSaving: boolean;

  procumlois: IProCumLoi[];

  editForm = this.fb.group({
    id: [],
    maLoi: [],
    cusCode: [],
    tenLoi: [],
    tenTiengAnh: [],
    label: [],
    stt: [],
    mauSac: [],
    qc: [],
    qa: [],
    checked: [],
    ghiChu: [],
    proCumLoiId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected proLoiService: ProLoiService,
    protected proCumLoiService: ProCumLoiService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proLoi }) => {
      this.updateForm(proLoi);
    });
    this.proCumLoiService
      .query()
      .subscribe((res: HttpResponse<IProCumLoi[]>) => (this.procumlois = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(proLoi: IProLoi) {
    this.editForm.patchValue({
      id: proLoi.id,
      maLoi: proLoi.maLoi,
      cusCode: proLoi.cusCode,
      tenLoi: proLoi.tenLoi,
      tenTiengAnh: proLoi.tenTiengAnh,
      label: proLoi.label,
      stt: proLoi.stt,
      mauSac: proLoi.mauSac,
      qc: proLoi.qc,
      qa: proLoi.qa,
      checked: proLoi.checked,
      ghiChu: proLoi.ghiChu,
      proCumLoiId: proLoi.proCumLoiId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proLoi = this.createFromForm();
    if (proLoi.id !== undefined) {
      this.subscribeToSaveResponse(this.proLoiService.update(proLoi));
    } else {
      this.subscribeToSaveResponse(this.proLoiService.create(proLoi));
    }
  }

  private createFromForm(): IProLoi {
    return {
      ...new ProLoi(),
      id: this.editForm.get(['id']).value,
      maLoi: this.editForm.get(['maLoi']).value,
      cusCode: this.editForm.get(['cusCode']).value,
      tenLoi: this.editForm.get(['tenLoi']).value,
      tenTiengAnh: this.editForm.get(['tenTiengAnh']).value,
      label: this.editForm.get(['label']).value,
      stt: this.editForm.get(['stt']).value,
      mauSac: this.editForm.get(['mauSac']).value,
      qc: this.editForm.get(['qc']).value,
      qa: this.editForm.get(['qa']).value,
      checked: this.editForm.get(['checked']).value,
      ghiChu: this.editForm.get(['ghiChu']).value,
      proCumLoiId: this.editForm.get(['proCumLoiId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProLoi>>) {
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

  trackProCumLoiById(index: number, item: IProCumLoi) {
    return item.id;
  }
}
