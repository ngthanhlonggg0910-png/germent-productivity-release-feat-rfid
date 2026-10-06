import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IProFinalChiTietLoi, ProFinalChiTietLoi } from 'app/shared/model/pro-final-chi-tiet-loi.model';
import { ProFinalChiTietLoiService } from './pro-final-chi-tiet-loi.service';
import { IProFinal } from 'app/shared/model/pro-final.model';
import { ProFinalService } from 'app/entities/pro-final/pro-final.service';
import { IProLoi } from 'app/shared/model/pro-loi.model';
import { ProLoiService } from 'app/entities/pro-loi/pro-loi.service';

@Component({
  selector: 'jhi-pro-final-chi-tiet-loi-update',
  templateUrl: './pro-final-chi-tiet-loi-update.component.html'
})
export class ProFinalChiTietLoiUpdateComponent implements OnInit {
  isSaving: boolean;

  profinals: IProFinal[];

  prolois: IProLoi[];

  editForm = this.fb.group({
    id: [],
    maLoi: [],
    thoiGian: [],
    dx: [],
    dy: [],
    isFront: [],
    isInside: [],
    warning: [],
    nguoiKiem: [],
    proFinalId: [],
    proLoiId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected proFinalChiTietLoiService: ProFinalChiTietLoiService,
    protected proFinalService: ProFinalService,
    protected proLoiService: ProLoiService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proFinalChiTietLoi }) => {
      this.updateForm(proFinalChiTietLoi);
    });
    this.proFinalService
      .query()
      .subscribe((res: HttpResponse<IProFinal[]>) => (this.profinals = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.proLoiService
      .query()
      .subscribe((res: HttpResponse<IProLoi[]>) => (this.prolois = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(proFinalChiTietLoi: IProFinalChiTietLoi) {
    this.editForm.patchValue({
      id: proFinalChiTietLoi.id,
      maLoi: proFinalChiTietLoi.maLoi,
      thoiGian: proFinalChiTietLoi.thoiGian != null ? proFinalChiTietLoi.thoiGian.format(DATE_TIME_FORMAT) : null,
      dx: proFinalChiTietLoi.dx,
      dy: proFinalChiTietLoi.dy,
      isFront: proFinalChiTietLoi.isFront,
      isInside: proFinalChiTietLoi.isInside,
      warning: proFinalChiTietLoi.warning,
      nguoiKiem: proFinalChiTietLoi.nguoiKiem,
      proFinalId: proFinalChiTietLoi.proFinalId,
      proLoiId: proFinalChiTietLoi.proLoiId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proFinalChiTietLoi = this.createFromForm();
    if (proFinalChiTietLoi.id !== undefined) {
      this.subscribeToSaveResponse(this.proFinalChiTietLoiService.update(proFinalChiTietLoi));
    } else {
      this.subscribeToSaveResponse(this.proFinalChiTietLoiService.create(proFinalChiTietLoi));
    }
  }

  private createFromForm(): IProFinalChiTietLoi {
    return {
      ...new ProFinalChiTietLoi(),
      id: this.editForm.get(['id']).value,
      maLoi: this.editForm.get(['maLoi']).value,
      thoiGian: this.editForm.get(['thoiGian']).value != null ? moment(this.editForm.get(['thoiGian']).value, DATE_TIME_FORMAT) : undefined,
      dx: this.editForm.get(['dx']).value,
      dy: this.editForm.get(['dy']).value,
      isFront: this.editForm.get(['isFront']).value,
      isInside: this.editForm.get(['isInside']).value,
      warning: this.editForm.get(['warning']).value,
      nguoiKiem: this.editForm.get(['nguoiKiem']).value,
      proFinalId: this.editForm.get(['proFinalId']).value,
      proLoiId: this.editForm.get(['proLoiId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProFinalChiTietLoi>>) {
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

  trackProFinalById(index: number, item: IProFinal) {
    return item.id;
  }

  trackProLoiById(index: number, item: IProLoi) {
    return item.id;
  }
}
