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
import { IProRefinalCCChiTietLoi, ProRefinalCCChiTietLoi } from 'app/shared/model/pro-refinal-cc-chi-tiet-loi.model';
import { ProRefinalCCChiTietLoiService } from './pro-refinal-cc-chi-tiet-loi.service';
import { IProRefinalCC } from 'app/shared/model/pro-refinal-cc.model';
import { ProRefinalCCService } from 'app/entities/pro-refinal-cc/pro-refinal-cc.service';
import { IProLoi } from 'app/shared/model/pro-loi.model';
import { ProLoiService } from 'app/entities/pro-loi/pro-loi.service';

@Component({
  selector: 'jhi-pro-refinal-cc-chi-tiet-loi-update',
  templateUrl: './pro-refinal-cc-chi-tiet-loi-update.component.html'
})
export class ProRefinalCCChiTietLoiUpdateComponent implements OnInit {
  isSaving: boolean;

  prorefinalccs: IProRefinalCC[];

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
    proRefinalCCId: [],
    proLoiId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected proRefinalCCChiTietLoiService: ProRefinalCCChiTietLoiService,
    protected proRefinalCCService: ProRefinalCCService,
    protected proLoiService: ProLoiService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proRefinalCCChiTietLoi }) => {
      this.updateForm(proRefinalCCChiTietLoi);
    });
    this.proRefinalCCService
      .query()
      .subscribe(
        (res: HttpResponse<IProRefinalCC[]>) => (this.prorefinalccs = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.proLoiService
      .query()
      .subscribe((res: HttpResponse<IProLoi[]>) => (this.prolois = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(proRefinalCCChiTietLoi: IProRefinalCCChiTietLoi) {
    this.editForm.patchValue({
      id: proRefinalCCChiTietLoi.id,
      maLoi: proRefinalCCChiTietLoi.maLoi,
      thoiGian: proRefinalCCChiTietLoi.thoiGian != null ? proRefinalCCChiTietLoi.thoiGian.format(DATE_TIME_FORMAT) : null,
      dx: proRefinalCCChiTietLoi.dx,
      dy: proRefinalCCChiTietLoi.dy,
      isFront: proRefinalCCChiTietLoi.isFront,
      isInside: proRefinalCCChiTietLoi.isInside,
      warning: proRefinalCCChiTietLoi.warning,
      nguoiKiem: proRefinalCCChiTietLoi.nguoiKiem,
      proRefinalCCId: proRefinalCCChiTietLoi.proRefinalCCId,
      proLoiId: proRefinalCCChiTietLoi.proLoiId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proRefinalCCChiTietLoi = this.createFromForm();
    if (proRefinalCCChiTietLoi.id !== undefined) {
      this.subscribeToSaveResponse(this.proRefinalCCChiTietLoiService.update(proRefinalCCChiTietLoi));
    } else {
      this.subscribeToSaveResponse(this.proRefinalCCChiTietLoiService.create(proRefinalCCChiTietLoi));
    }
  }

  private createFromForm(): IProRefinalCCChiTietLoi {
    return {
      ...new ProRefinalCCChiTietLoi(),
      id: this.editForm.get(['id']).value,
      maLoi: this.editForm.get(['maLoi']).value,
      thoiGian: this.editForm.get(['thoiGian']).value != null ? moment(this.editForm.get(['thoiGian']).value, DATE_TIME_FORMAT) : undefined,
      dx: this.editForm.get(['dx']).value,
      dy: this.editForm.get(['dy']).value,
      isFront: this.editForm.get(['isFront']).value,
      isInside: this.editForm.get(['isInside']).value,
      warning: this.editForm.get(['warning']).value,
      nguoiKiem: this.editForm.get(['nguoiKiem']).value,
      proRefinalCCId: this.editForm.get(['proRefinalCCId']).value,
      proLoiId: this.editForm.get(['proLoiId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProRefinalCCChiTietLoi>>) {
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

  trackProRefinalCCById(index: number, item: IProRefinalCC) {
    return item.id;
  }

  trackProLoiById(index: number, item: IProLoi) {
    return item.id;
  }
}
