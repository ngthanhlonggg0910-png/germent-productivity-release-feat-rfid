import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IProNSCL, ProNSCL } from 'app/shared/model/pro-nscl.model';
import { ProNSCLService } from './pro-nscl.service';
import { IProKeHoachSanXuat } from 'app/shared/model/pro-ke-hoach-san-xuat.model';
import { ProKeHoachSanXuatService } from 'app/entities/pro-ke-hoach-san-xuat/pro-ke-hoach-san-xuat.service';
import { IProCheckPoint } from 'app/shared/model/pro-check-point.model';
import { ProCheckPointService } from 'app/entities/pro-check-point/pro-check-point.service';

type SelectableEntity = IProKeHoachSanXuat | IProCheckPoint;

@Component({
  selector: 'jhi-pro-nscl-update',
  templateUrl: './pro-nscl-update.component.html'
})
export class ProNSCLUpdateComponent implements OnInit {
  isSaving = false;
  prokehoachsanxuats: IProKeHoachSanXuat[] = [];
  procheckpoints: IProCheckPoint[] = [];

  editForm = this.fb.group({
    id: [],
    taiKhoan: [],
    ngaySanXuat: [],
    stt: [],
    mucTieuNgay: [],
    thucHienNgay: [],
    loiNgay: [],
    suaNgay: [],
    btpCap: [],
    qty1h: [null, [Validators.required]],
    qty2h: [null, [Validators.required]],
    qty3h: [null, [Validators.required]],
    qty4h: [null, [Validators.required]],
    qty5h: [null, [Validators.required]],
    qty6h: [null, [Validators.required]],
    qty7h: [null, [Validators.required]],
    qty8h: [null, [Validators.required]],
    qty9h: [null, [Validators.required]],
    qty10h: [null, [Validators.required]],
    qty11h: [null, [Validators.required]],
    qty12h: [null, [Validators.required]],
    qty13h: [null, [Validators.required]],
    qty14h: [null, [Validators.required]],
    reject1h: [null, [Validators.required]],
    reject2h: [null, [Validators.required]],
    reject3h: [null, [Validators.required]],
    reject4h: [null, [Validators.required]],
    reject5h: [null, [Validators.required]],
    reject6h: [null, [Validators.required]],
    reject7h: [null, [Validators.required]],
    reject8h: [null, [Validators.required]],
    reject9h: [null, [Validators.required]],
    reject10h: [null, [Validators.required]],
    reject11h: [null, [Validators.required]],
    reject12h: [null, [Validators.required]],
    reject13h: [null, [Validators.required]],
    reject14h: [null, [Validators.required]],
    man1h: [null, [Validators.required]],
    man2h: [null, [Validators.required]],
    man3h: [null, [Validators.required]],
    man4h: [null, [Validators.required]],
    man5h: [null, [Validators.required]],
    man6h: [null, [Validators.required]],
    man7h: [null, [Validators.required]],
    man8h: [null, [Validators.required]],
    man9h: [null, [Validators.required]],
    man10h: [null, [Validators.required]],
    man11h: [null, [Validators.required]],
    man12h: [null, [Validators.required]],
    man13h: [null, [Validators.required]],
    man14h: [null, [Validators.required]],
    manOnly7Hours: [null, [Validators.required]],
    fix1h: [null, [Validators.required]],
    fix2h: [null, [Validators.required]],
    fix3h: [null, [Validators.required]],
    fix4h: [null, [Validators.required]],
    fix5h: [null, [Validators.required]],
    fix6h: [null, [Validators.required]],
    fix7h: [null, [Validators.required]],
    fix8h: [null, [Validators.required]],
    fix9h: [null, [Validators.required]],
    fix10h: [null, [Validators.required]],
    fix11h: [null, [Validators.required]],
    fix12h: [null, [Validators.required]],
    fix13h: [null, [Validators.required]],
    fix14h: [null, [Validators.required]],
    proKeHoachSanXuatId: [],
    proCheckPointId: []
  });

  constructor(
    protected proNSCLService: ProNSCLService,
    protected proKeHoachSanXuatService: ProKeHoachSanXuatService,
    protected proCheckPointService: ProCheckPointService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ proNSCL }) => {
      if (!proNSCL.id) {
        const today = moment().startOf('day');
        proNSCL.ngaySanXuat = today;
      }

      this.updateForm(proNSCL);

      this.proKeHoachSanXuatService
        .query()
        .subscribe((res: HttpResponse<IProKeHoachSanXuat[]>) => (this.prokehoachsanxuats = res.body || []));

      this.proCheckPointService.query().subscribe((res: HttpResponse<IProCheckPoint[]>) => (this.procheckpoints = res.body || []));
    });
  }

  updateForm(proNSCL: IProNSCL): void {
    this.editForm.patchValue({
      id: proNSCL.id,
      taiKhoan: proNSCL.taiKhoan,
      ngaySanXuat: proNSCL.ngaySanXuat ? proNSCL.ngaySanXuat.format(DATE_TIME_FORMAT) : null,
      stt: proNSCL.stt,
      mucTieuNgay: proNSCL.mucTieuNgay,
      thucHienNgay: proNSCL.thucHienNgay,
      loiNgay: proNSCL.loiNgay,
      suaNgay: proNSCL.suaNgay,
      btpCap: proNSCL.btpCap,
      qty1h: proNSCL.qty1h,
      qty2h: proNSCL.qty2h,
      qty3h: proNSCL.qty3h,
      qty4h: proNSCL.qty4h,
      qty5h: proNSCL.qty5h,
      qty6h: proNSCL.qty6h,
      qty7h: proNSCL.qty7h,
      qty8h: proNSCL.qty8h,
      qty9h: proNSCL.qty9h,
      qty10h: proNSCL.qty10h,
      qty11h: proNSCL.qty11h,
      qty12h: proNSCL.qty12h,
      qty13h: proNSCL.qty13h,
      qty14h: proNSCL.qty14h,
      reject1h: proNSCL.reject1h,
      reject2h: proNSCL.reject2h,
      reject3h: proNSCL.reject3h,
      reject4h: proNSCL.reject4h,
      reject5h: proNSCL.reject5h,
      reject6h: proNSCL.reject6h,
      reject7h: proNSCL.reject7h,
      reject8h: proNSCL.reject8h,
      reject9h: proNSCL.reject9h,
      reject10h: proNSCL.reject10h,
      reject11h: proNSCL.reject11h,
      reject12h: proNSCL.reject12h,
      reject13h: proNSCL.reject13h,
      reject14h: proNSCL.reject14h,
      man1h: proNSCL.man1h,
      man2h: proNSCL.man2h,
      man3h: proNSCL.man3h,
      man4h: proNSCL.man4h,
      man5h: proNSCL.man5h,
      man6h: proNSCL.man6h,
      man7h: proNSCL.man7h,
      man8h: proNSCL.man8h,
      man9h: proNSCL.man9h,
      man10h: proNSCL.man10h,
      man11h: proNSCL.man11h,
      man12h: proNSCL.man12h,
      man13h: proNSCL.man13h,
      man14h: proNSCL.man14h,
      manOnly7Hours: proNSCL.manOnly7Hours,
      fix1h: proNSCL.fix1h,
      fix2h: proNSCL.fix2h,
      fix3h: proNSCL.fix3h,
      fix4h: proNSCL.fix4h,
      fix5h: proNSCL.fix5h,
      fix6h: proNSCL.fix6h,
      fix7h: proNSCL.fix7h,
      fix8h: proNSCL.fix8h,
      fix9h: proNSCL.fix9h,
      fix10h: proNSCL.fix10h,
      fix11h: proNSCL.fix11h,
      fix12h: proNSCL.fix12h,
      fix13h: proNSCL.fix13h,
      fix14h: proNSCL.fix14h,
      proKeHoachSanXuatId: proNSCL.proKeHoachSanXuatId,
      proCheckPointId: proNSCL.proCheckPointId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const proNSCL = this.createFromForm();
    if (proNSCL.id !== undefined) {
      this.subscribeToSaveResponse(this.proNSCLService.update(proNSCL));
    } else {
      this.subscribeToSaveResponse(this.proNSCLService.create(proNSCL));
    }
  }

  private createFromForm(): IProNSCL {
    return {
      ...new ProNSCL(),
      id: this.editForm.get(['id'])!.value,
      taiKhoan: this.editForm.get(['taiKhoan'])!.value,
      ngaySanXuat: this.editForm.get(['ngaySanXuat'])!.value
        ? moment(this.editForm.get(['ngaySanXuat'])!.value, DATE_TIME_FORMAT)
        : undefined,
      stt: this.editForm.get(['stt'])!.value,
      mucTieuNgay: this.editForm.get(['mucTieuNgay'])!.value,
      thucHienNgay: this.editForm.get(['thucHienNgay'])!.value,
      loiNgay: this.editForm.get(['loiNgay'])!.value,
      suaNgay: this.editForm.get(['suaNgay'])!.value,
      btpCap: this.editForm.get(['btpCap'])!.value,
      qty1h: this.editForm.get(['qty1h'])!.value,
      qty2h: this.editForm.get(['qty2h'])!.value,
      qty3h: this.editForm.get(['qty3h'])!.value,
      qty4h: this.editForm.get(['qty4h'])!.value,
      qty5h: this.editForm.get(['qty5h'])!.value,
      qty6h: this.editForm.get(['qty6h'])!.value,
      qty7h: this.editForm.get(['qty7h'])!.value,
      qty8h: this.editForm.get(['qty8h'])!.value,
      qty9h: this.editForm.get(['qty9h'])!.value,
      qty10h: this.editForm.get(['qty10h'])!.value,
      qty11h: this.editForm.get(['qty11h'])!.value,
      qty12h: this.editForm.get(['qty12h'])!.value,
      qty13h: this.editForm.get(['qty13h'])!.value,
      qty14h: this.editForm.get(['qty14h'])!.value,
      reject1h: this.editForm.get(['reject1h'])!.value,
      reject2h: this.editForm.get(['reject2h'])!.value,
      reject3h: this.editForm.get(['reject3h'])!.value,
      reject4h: this.editForm.get(['reject4h'])!.value,
      reject5h: this.editForm.get(['reject5h'])!.value,
      reject6h: this.editForm.get(['reject6h'])!.value,
      reject7h: this.editForm.get(['reject7h'])!.value,
      reject8h: this.editForm.get(['reject8h'])!.value,
      reject9h: this.editForm.get(['reject9h'])!.value,
      reject10h: this.editForm.get(['reject10h'])!.value,
      reject11h: this.editForm.get(['reject11h'])!.value,
      reject12h: this.editForm.get(['reject12h'])!.value,
      reject13h: this.editForm.get(['reject13h'])!.value,
      reject14h: this.editForm.get(['reject14h'])!.value,
      man1h: this.editForm.get(['man1h'])!.value,
      man2h: this.editForm.get(['man2h'])!.value,
      man3h: this.editForm.get(['man3h'])!.value,
      man4h: this.editForm.get(['man4h'])!.value,
      man5h: this.editForm.get(['man5h'])!.value,
      man6h: this.editForm.get(['man6h'])!.value,
      man7h: this.editForm.get(['man7h'])!.value,
      man8h: this.editForm.get(['man8h'])!.value,
      man9h: this.editForm.get(['man9h'])!.value,
      man10h: this.editForm.get(['man10h'])!.value,
      man11h: this.editForm.get(['man11h'])!.value,
      man12h: this.editForm.get(['man12h'])!.value,
      man13h: this.editForm.get(['man13h'])!.value,
      man14h: this.editForm.get(['man14h'])!.value,
      manOnly7Hours: this.editForm.get(['manOnly7Hours'])!.value,
      fix1h: this.editForm.get(['fix1h'])!.value,
      fix2h: this.editForm.get(['fix2h'])!.value,
      fix3h: this.editForm.get(['fix3h'])!.value,
      fix4h: this.editForm.get(['fix4h'])!.value,
      fix5h: this.editForm.get(['fix5h'])!.value,
      fix6h: this.editForm.get(['fix6h'])!.value,
      fix7h: this.editForm.get(['fix7h'])!.value,
      fix8h: this.editForm.get(['fix8h'])!.value,
      fix9h: this.editForm.get(['fix9h'])!.value,
      fix10h: this.editForm.get(['fix10h'])!.value,
      fix11h: this.editForm.get(['fix11h'])!.value,
      fix12h: this.editForm.get(['fix12h'])!.value,
      fix13h: this.editForm.get(['fix13h'])!.value,
      fix14h: this.editForm.get(['fix14h'])!.value,
      proKeHoachSanXuatId: this.editForm.get(['proKeHoachSanXuatId'])!.value,
      proCheckPointId: this.editForm.get(['proCheckPointId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProNSCL>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
