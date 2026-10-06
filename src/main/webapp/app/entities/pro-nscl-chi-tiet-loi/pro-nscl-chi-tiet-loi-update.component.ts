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
import { IProNSCLChiTietLoi, ProNSCLChiTietLoi } from 'app/shared/model/pro-nscl-chi-tiet-loi.model';
import { ProNSCLChiTietLoiService } from './pro-nscl-chi-tiet-loi.service';
import { IProNSCL } from 'app/shared/model/pro-nscl.model';
import { ProNSCLService } from 'app/entities/pro-nscl/pro-nscl.service';
import { IProLoi } from 'app/shared/model/pro-loi.model';
import { ProLoiService } from 'app/entities/pro-loi/pro-loi.service';

@Component({
  selector: 'jhi-pro-nscl-chi-tiet-loi-update',
  templateUrl: './pro-nscl-chi-tiet-loi-update.component.html'
})
export class ProNSCLChiTietLoiUpdateComponent implements OnInit {
  isSaving: boolean;

  pronscls: IProNSCL[];

  prolois: IProLoi[];

  editForm = this.fb.group({
    id: [],
    maLoi: [null, [Validators.required]],
    thoiGian: [null, [Validators.required]],
    gioLam: [],
    dx: [],
    dy: [],
    isFront: [],
    isInside: [],
    proNSCLId: [],
    proLoiId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected proNSCLChiTietLoiService: ProNSCLChiTietLoiService,
    protected proNSCLService: ProNSCLService,
    protected proLoiService: ProLoiService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proNSCLChiTietLoi }) => {
      this.updateForm(proNSCLChiTietLoi);
    });
    this.proNSCLService
      .query()
      .subscribe((res: HttpResponse<IProNSCL[]>) => (this.pronscls = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.proLoiService
      .query()
      .subscribe((res: HttpResponse<IProLoi[]>) => (this.prolois = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(proNSCLChiTietLoi: IProNSCLChiTietLoi) {
    this.editForm.patchValue({
      id: proNSCLChiTietLoi.id,
      maLoi: proNSCLChiTietLoi.maLoi,
      thoiGian: proNSCLChiTietLoi.thoiGian != null ? proNSCLChiTietLoi.thoiGian.format(DATE_TIME_FORMAT) : null,
      gioLam: proNSCLChiTietLoi.gioLam,
      dx: proNSCLChiTietLoi.dx,
      dy: proNSCLChiTietLoi.dy,
      isFront: proNSCLChiTietLoi.isFront,
      isInside: proNSCLChiTietLoi.isInside,
      proNSCLId: proNSCLChiTietLoi.proNSCLId,
      proLoiId: proNSCLChiTietLoi.proLoiId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proNSCLChiTietLoi = this.createFromForm();
    if (proNSCLChiTietLoi.id !== undefined) {
      this.subscribeToSaveResponse(this.proNSCLChiTietLoiService.update(proNSCLChiTietLoi));
    } else {
      this.subscribeToSaveResponse(this.proNSCLChiTietLoiService.create(proNSCLChiTietLoi));
    }
  }

  private createFromForm(): IProNSCLChiTietLoi {
    return {
      ...new ProNSCLChiTietLoi(),
      id: this.editForm.get(['id']).value,
      maLoi: this.editForm.get(['maLoi']).value,
      thoiGian: this.editForm.get(['thoiGian']).value != null ? moment(this.editForm.get(['thoiGian']).value, DATE_TIME_FORMAT) : undefined,
      gioLam: this.editForm.get(['gioLam']).value,
      dx: this.editForm.get(['dx']).value,
      dy: this.editForm.get(['dy']).value,
      isFront: this.editForm.get(['isFront']).value,
      isInside: this.editForm.get(['isInside']).value,
      proNSCLId: this.editForm.get(['proNSCLId']).value,
      proLoiId: this.editForm.get(['proLoiId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProNSCLChiTietLoi>>) {
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

  trackProNSCLById(index: number, item: IProNSCL) {
    return item.id;
  }

  trackProLoiById(index: number, item: IProLoi) {
    return item.id;
  }
}
