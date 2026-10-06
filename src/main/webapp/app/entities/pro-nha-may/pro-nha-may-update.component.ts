import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IProNhaMay, ProNhaMay } from 'app/shared/model/pro-nha-may.model';
import { ProNhaMayService } from './pro-nha-may.service';
import { IProCongTy } from 'app/shared/model/pro-cong-ty.model';
import { ProCongTyService } from 'app/entities/pro-cong-ty/pro-cong-ty.service';

@Component({
  selector: 'jhi-pro-nha-may-update',
  templateUrl: './pro-nha-may-update.component.html'
})
export class ProNhaMayUpdateComponent implements OnInit {
  isSaving: boolean;

  procongties: IProCongTy[];

  editForm = this.fb.group({
    id: [],
    ten: [],
    stt: [],
    proCongTyId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected proNhaMayService: ProNhaMayService,
    protected proCongTyService: ProCongTyService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proNhaMay }) => {
      this.updateForm(proNhaMay);
    });
    this.proCongTyService
      .query()
      .subscribe((res: HttpResponse<IProCongTy[]>) => (this.procongties = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(proNhaMay: IProNhaMay) {
    this.editForm.patchValue({
      id: proNhaMay.id,
      ten: proNhaMay.ten,
      stt: proNhaMay.stt,
      proCongTyId: proNhaMay.proCongTyId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proNhaMay = this.createFromForm();
    if (proNhaMay.id !== undefined) {
      this.subscribeToSaveResponse(this.proNhaMayService.update(proNhaMay));
    } else {
      this.subscribeToSaveResponse(this.proNhaMayService.create(proNhaMay));
    }
  }

  private createFromForm(): IProNhaMay {
    return {
      ...new ProNhaMay(),
      id: this.editForm.get(['id']).value,
      ten: this.editForm.get(['ten']).value,
      stt: this.editForm.get(['stt']).value,
      proCongTyId: this.editForm.get(['proCongTyId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProNhaMay>>) {
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

  trackProCongTyById(index: number, item: IProCongTy) {
    return item.id;
  }
}
