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
import { IProRefinalChiTietLoi, ProRefinalChiTietLoi } from 'app/shared/model/pro-refinal-chi-tiet-loi.model';
import { ProRefinalChiTietLoiService } from './pro-refinal-chi-tiet-loi.service';
import { IProRefinal } from 'app/shared/model/pro-refinal.model';
import { ProRefinalService } from 'app/entities/pro-refinal/pro-refinal.service';
import { IProLoi } from 'app/shared/model/pro-loi.model';
import { ProLoiService } from 'app/entities/pro-loi/pro-loi.service';

@Component({
  selector: 'jhi-pro-refinal-chi-tiet-loi-update',
  templateUrl: './pro-refinal-chi-tiet-loi-update.component.html'
})
export class ProRefinalChiTietLoiUpdateComponent implements OnInit {
  isSaving: boolean;

  prorefinals: IProRefinal[];

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
    proRefinalId: [],
    proLoiId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected proRefinalChiTietLoiService: ProRefinalChiTietLoiService,
    protected proRefinalService: ProRefinalService,
    protected proLoiService: ProLoiService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proRefinalChiTietLoi }) => {
      this.updateForm(proRefinalChiTietLoi);
    });
    this.proRefinalService
      .query()
      .subscribe(
        (res: HttpResponse<IProRefinal[]>) => (this.prorefinals = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.proLoiService
      .query()
      .subscribe((res: HttpResponse<IProLoi[]>) => (this.prolois = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(proRefinalChiTietLoi: IProRefinalChiTietLoi) {
    this.editForm.patchValue({
      id: proRefinalChiTietLoi.id,
      maLoi: proRefinalChiTietLoi.maLoi,
      thoiGian: proRefinalChiTietLoi.thoiGian != null ? proRefinalChiTietLoi.thoiGian.format(DATE_TIME_FORMAT) : null,
      dx: proRefinalChiTietLoi.dx,
      dy: proRefinalChiTietLoi.dy,
      isFront: proRefinalChiTietLoi.isFront,
      isInside: proRefinalChiTietLoi.isInside,
      warning: proRefinalChiTietLoi.warning,
      proRefinalId: proRefinalChiTietLoi.proRefinalId,
      proLoiId: proRefinalChiTietLoi.proLoiId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proRefinalChiTietLoi = this.createFromForm();
    if (proRefinalChiTietLoi.id !== undefined) {
      this.subscribeToSaveResponse(this.proRefinalChiTietLoiService.update(proRefinalChiTietLoi));
    } else {
      this.subscribeToSaveResponse(this.proRefinalChiTietLoiService.create(proRefinalChiTietLoi));
    }
  }

  private createFromForm(): IProRefinalChiTietLoi {
    return {
      ...new ProRefinalChiTietLoi(),
      id: this.editForm.get(['id']).value,
      maLoi: this.editForm.get(['maLoi']).value,
      thoiGian: this.editForm.get(['thoiGian']).value != null ? moment(this.editForm.get(['thoiGian']).value, DATE_TIME_FORMAT) : undefined,
      dx: this.editForm.get(['dx']).value,
      dy: this.editForm.get(['dy']).value,
      isFront: this.editForm.get(['isFront']).value,
      isInside: this.editForm.get(['isInside']).value,
      warning: this.editForm.get(['warning']).value,
      proRefinalId: this.editForm.get(['proRefinalId']).value,
      proLoiId: this.editForm.get(['proLoiId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProRefinalChiTietLoi>>) {
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

  trackProRefinalById(index: number, item: IProRefinal) {
    return item.id;
  }

  trackProLoiById(index: number, item: IProLoi) {
    return item.id;
  }
}
