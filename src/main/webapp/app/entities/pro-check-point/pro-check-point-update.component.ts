import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IProCheckPoint, ProCheckPoint } from 'app/shared/model/pro-check-point.model';
import { ProCheckPointService } from './pro-check-point.service';
import { IProChuyen } from 'app/shared/model/pro-chuyen.model';
import { ProChuyenService } from 'app/entities/pro-chuyen/pro-chuyen.service';
import { IProCongDoan } from 'app/shared/model/pro-cong-doan.model';
import { ProCongDoanService } from 'app/entities/pro-cong-doan/pro-cong-doan.service';

@Component({
  selector: 'jhi-pro-check-point-update',
  templateUrl: './pro-check-point-update.component.html'
})
export class ProCheckPointUpdateComponent implements OnInit {
  isSaving: boolean;

  prochuyens: IProChuyen[];

  procongdoans: IProCongDoan[];

  editForm = this.fb.group({
    id: [],
    ten: [],
    proChuyenId: [],
    proCongDoanId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected proCheckPointService: ProCheckPointService,
    protected proChuyenService: ProChuyenService,
    protected proCongDoanService: ProCongDoanService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proCheckPoint }) => {
      this.updateForm(proCheckPoint);
    });
    this.proChuyenService
      .query()
      .subscribe((res: HttpResponse<IProChuyen[]>) => (this.prochuyens = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.proCongDoanService
      .query()
      .subscribe(
        (res: HttpResponse<IProCongDoan[]>) => (this.procongdoans = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(proCheckPoint: IProCheckPoint) {
    this.editForm.patchValue({
      id: proCheckPoint.id,
      ten: proCheckPoint.ten,
      proChuyenId: proCheckPoint.proChuyenId,
      proCongDoanId: proCheckPoint.proCongDoanId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proCheckPoint = this.createFromForm();
    if (proCheckPoint.id !== undefined) {
      this.subscribeToSaveResponse(this.proCheckPointService.update(proCheckPoint));
    } else {
      this.subscribeToSaveResponse(this.proCheckPointService.create(proCheckPoint));
    }
  }

  private createFromForm(): IProCheckPoint {
    return {
      ...new ProCheckPoint(),
      id: this.editForm.get(['id']).value,
      ten: this.editForm.get(['ten']).value,
      proChuyenId: this.editForm.get(['proChuyenId']).value,
      proCongDoanId: this.editForm.get(['proCongDoanId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProCheckPoint>>) {
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

  trackProChuyenById(index: number, item: IProChuyen) {
    return item.id;
  }

  trackProCongDoanById(index: number, item: IProCongDoan) {
    return item.id;
  }
}
