import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IProIssueLoi, ProIssueLoi } from 'app/shared/model/pro-issue-loi.model';
import { ProIssueLoiService } from './pro-issue-loi.service';
import { IProIssueCumLoi } from 'app/shared/model/pro-issue-cum-loi.model';
import { ProIssueCumLoiService } from 'app/entities/pro-issue-cum-loi/pro-issue-cum-loi.service';

@Component({
  selector: 'jhi-pro-issue-loi-update',
  templateUrl: './pro-issue-loi-update.component.html'
})
export class ProIssueLoiUpdateComponent implements OnInit {
  isSaving: boolean;

  proissuecumlois: IProIssueCumLoi[];

  editForm = this.fb.group({
    id: [],
    maLoi: [],
    cusCode: [],
    tenLoi: [],
    tenTiengAnh: [],
    label: [],
    stt: [],
    mauSac: [],
    ghiChu: [],
    proIssueCumLoiId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected proIssueLoiService: ProIssueLoiService,
    protected proIssueCumLoiService: ProIssueCumLoiService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proIssueLoi }) => {
      this.updateForm(proIssueLoi);
    });
    this.proIssueCumLoiService
      .query()
      .subscribe(
        (res: HttpResponse<IProIssueCumLoi[]>) => (this.proissuecumlois = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(proIssueLoi: IProIssueLoi) {
    this.editForm.patchValue({
      id: proIssueLoi.id,
      maLoi: proIssueLoi.maLoi,
      cusCode: proIssueLoi.cusCode,
      tenLoi: proIssueLoi.tenLoi,
      tenTiengAnh: proIssueLoi.tenTiengAnh,
      label: proIssueLoi.label,
      stt: proIssueLoi.stt,
      mauSac: proIssueLoi.mauSac,
      ghiChu: proIssueLoi.ghiChu,
      proIssueCumLoiId: proIssueLoi.proIssueCumLoiId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proIssueLoi = this.createFromForm();
    if (proIssueLoi.id !== undefined) {
      this.subscribeToSaveResponse(this.proIssueLoiService.update(proIssueLoi));
    } else {
      this.subscribeToSaveResponse(this.proIssueLoiService.create(proIssueLoi));
    }
  }

  private createFromForm(): IProIssueLoi {
    return {
      ...new ProIssueLoi(),
      id: this.editForm.get(['id']).value,
      maLoi: this.editForm.get(['maLoi']).value,
      cusCode: this.editForm.get(['cusCode']).value,
      tenLoi: this.editForm.get(['tenLoi']).value,
      tenTiengAnh: this.editForm.get(['tenTiengAnh']).value,
      label: this.editForm.get(['label']).value,
      stt: this.editForm.get(['stt']).value,
      mauSac: this.editForm.get(['mauSac']).value,
      ghiChu: this.editForm.get(['ghiChu']).value,
      proIssueCumLoiId: this.editForm.get(['proIssueCumLoiId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProIssueLoi>>) {
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

  trackProIssueCumLoiById(index: number, item: IProIssueCumLoi) {
    return item.id;
  }
}
