import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IProTaiKhoanCheckPoint, ProTaiKhoanCheckPoint } from 'app/shared/model/pro-tai-khoan-check-point.model';
import { ProTaiKhoanCheckPointService } from './pro-tai-khoan-check-point.service';
import { IProCheckPoint } from 'app/shared/model/pro-check-point.model';
import { ProCheckPointService } from 'app/entities/pro-check-point/pro-check-point.service';

@Component({
  selector: 'jhi-pro-tai-khoan-check-point-update',
  templateUrl: './pro-tai-khoan-check-point-update.component.html'
})
export class ProTaiKhoanCheckPointUpdateComponent implements OnInit {
  isSaving: boolean;

  procheckpoints: IProCheckPoint[];

  editForm = this.fb.group({
    id: [],
    taiKhoan: [],
    checked: [],
    proCheckPointId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected proTaiKhoanCheckPointService: ProTaiKhoanCheckPointService,
    protected proCheckPointService: ProCheckPointService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proTaiKhoanCheckPoint }) => {
      this.updateForm(proTaiKhoanCheckPoint);
    });
    this.proCheckPointService
      .query()
      .subscribe(
        (res: HttpResponse<IProCheckPoint[]>) => (this.procheckpoints = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(proTaiKhoanCheckPoint: IProTaiKhoanCheckPoint) {
    this.editForm.patchValue({
      id: proTaiKhoanCheckPoint.id,
      taiKhoan: proTaiKhoanCheckPoint.taiKhoan,
      checked: proTaiKhoanCheckPoint.checked,
      proCheckPointId: proTaiKhoanCheckPoint.proCheckPointId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proTaiKhoanCheckPoint = this.createFromForm();
    if (proTaiKhoanCheckPoint.id !== undefined) {
      this.subscribeToSaveResponse(this.proTaiKhoanCheckPointService.update(proTaiKhoanCheckPoint));
    } else {
      this.subscribeToSaveResponse(this.proTaiKhoanCheckPointService.create(proTaiKhoanCheckPoint));
    }
  }

  private createFromForm(): IProTaiKhoanCheckPoint {
    return {
      ...new ProTaiKhoanCheckPoint(),
      id: this.editForm.get(['id']).value,
      taiKhoan: this.editForm.get(['taiKhoan']).value,
      checked: this.editForm.get(['checked']).value,
      proCheckPointId: this.editForm.get(['proCheckPointId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProTaiKhoanCheckPoint>>) {
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

  trackProCheckPointById(index: number, item: IProCheckPoint) {
    return item.id;
  }
}
