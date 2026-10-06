import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IProNhomCongDoan, ProNhomCongDoan } from 'app/shared/model/pro-nhom-cong-doan.model';
import { ProNhomCongDoanService } from './pro-nhom-cong-doan.service';

@Component({
  selector: 'jhi-pro-nhom-cong-doan-update',
  templateUrl: './pro-nhom-cong-doan-update.component.html'
})
export class ProNhomCongDoanUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    ten: [],
    checkNangSuat: []
  });

  constructor(
    protected proNhomCongDoanService: ProNhomCongDoanService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proNhomCongDoan }) => {
      this.updateForm(proNhomCongDoan);
    });
  }

  updateForm(proNhomCongDoan: IProNhomCongDoan) {
    this.editForm.patchValue({
      id: proNhomCongDoan.id,
      ten: proNhomCongDoan.ten,
      checkNangSuat: proNhomCongDoan.checkNangSuat
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proNhomCongDoan = this.createFromForm();
    if (proNhomCongDoan.id !== undefined) {
      this.subscribeToSaveResponse(this.proNhomCongDoanService.update(proNhomCongDoan));
    } else {
      this.subscribeToSaveResponse(this.proNhomCongDoanService.create(proNhomCongDoan));
    }
  }

  private createFromForm(): IProNhomCongDoan {
    return {
      ...new ProNhomCongDoan(),
      id: this.editForm.get(['id']).value,
      ten: this.editForm.get(['ten']).value,
      checkNangSuat: this.editForm.get(['checkNangSuat']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProNhomCongDoan>>) {
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
