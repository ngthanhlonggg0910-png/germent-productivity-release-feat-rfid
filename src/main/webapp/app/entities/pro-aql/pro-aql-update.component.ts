import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IProAql, ProAql } from 'app/shared/model/pro-aql.model';
import { ProAqlService } from './pro-aql.service';

@Component({
  selector: 'jhi-pro-aql-update',
  templateUrl: './pro-aql-update.component.html'
})
export class ProAqlUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    aql: [],
    soLuongDonHangTu: [],
    soLuongDonHangDen: [],
    soLuongKiem: [],
    soLuongSeFail: [],
    soLuongSePass: []
  });

  constructor(protected proAqlService: ProAqlService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proAql }) => {
      this.updateForm(proAql);
    });
  }

  updateForm(proAql: IProAql) {
    this.editForm.patchValue({
      id: proAql.id,
      aql: proAql.aql,
      soLuongDonHangTu: proAql.soLuongDonHangTu,
      soLuongDonHangDen: proAql.soLuongDonHangDen,
      soLuongKiem: proAql.soLuongKiem,
      soLuongSeFail: proAql.soLuongSeFail,
      soLuongSePass: proAql.soLuongSePass
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proAql = this.createFromForm();
    if (proAql.id !== undefined) {
      this.subscribeToSaveResponse(this.proAqlService.update(proAql));
    } else {
      this.subscribeToSaveResponse(this.proAqlService.create(proAql));
    }
  }

  private createFromForm(): IProAql {
    return {
      ...new ProAql(),
      id: this.editForm.get(['id']).value,
      aql: this.editForm.get(['aql']).value,
      soLuongDonHangTu: this.editForm.get(['soLuongDonHangTu']).value,
      soLuongDonHangDen: this.editForm.get(['soLuongDonHangDen']).value,
      soLuongKiem: this.editForm.get(['soLuongKiem']).value,
      soLuongSeFail: this.editForm.get(['soLuongSeFail']).value,
      soLuongSePass: this.editForm.get(['soLuongSePass']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProAql>>) {
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
