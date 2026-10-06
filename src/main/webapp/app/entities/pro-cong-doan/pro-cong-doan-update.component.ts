import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IProCongDoan, ProCongDoan } from 'app/shared/model/pro-cong-doan.model';
import { ProCongDoanService } from './pro-cong-doan.service';
import { IProNhomCongDoan } from 'app/shared/model/pro-nhom-cong-doan.model';
import { ProNhomCongDoanService } from 'app/entities/pro-nhom-cong-doan/pro-nhom-cong-doan.service';

@Component({
  selector: 'jhi-pro-cong-doan-update',
  templateUrl: './pro-cong-doan-update.component.html'
})
export class ProCongDoanUpdateComponent implements OnInit {
  isSaving: boolean;

  pronhomcongdoans: IProNhomCongDoan[];

  editForm = this.fb.group({
    id: [],
    ten: [],
    nhomCongDoan: [],
    proNhomCongDoanId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected proCongDoanService: ProCongDoanService,
    protected proNhomCongDoanService: ProNhomCongDoanService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proCongDoan }) => {
      this.updateForm(proCongDoan);
    });
    this.proNhomCongDoanService
      .query()
      .subscribe(
        (res: HttpResponse<IProNhomCongDoan[]>) => (this.pronhomcongdoans = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(proCongDoan: IProCongDoan) {
    this.editForm.patchValue({
      id: proCongDoan.id,
      ten: proCongDoan.ten,
      nhomCongDoan: proCongDoan.nhomCongDoan,
      proNhomCongDoanId: proCongDoan.proNhomCongDoanId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proCongDoan = this.createFromForm();
    if (proCongDoan.id !== undefined) {
      this.subscribeToSaveResponse(this.proCongDoanService.update(proCongDoan));
    } else {
      this.subscribeToSaveResponse(this.proCongDoanService.create(proCongDoan));
    }
  }

  private createFromForm(): IProCongDoan {
    return {
      ...new ProCongDoan(),
      id: this.editForm.get(['id']).value,
      ten: this.editForm.get(['ten']).value,
      nhomCongDoan: this.editForm.get(['nhomCongDoan']).value,
      proNhomCongDoanId: this.editForm.get(['proNhomCongDoanId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProCongDoan>>) {
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

  trackProNhomCongDoanById(index: number, item: IProNhomCongDoan) {
    return item.id;
  }
}
