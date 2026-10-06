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
import { IProKeHoachSanXuat, ProKeHoachSanXuat } from 'app/shared/model/pro-ke-hoach-san-xuat.model';
import { ProKeHoachSanXuatService } from './pro-ke-hoach-san-xuat.service';
import { IProChuyen } from 'app/shared/model/pro-chuyen.model';
import { ProChuyenService } from 'app/entities/pro-chuyen/pro-chuyen.service';
import { IProSanPham } from 'app/shared/model/pro-san-pham.model';
import { ProSanPhamService } from 'app/entities/pro-san-pham/pro-san-pham.service';

@Component({
  selector: 'jhi-pro-ke-hoach-san-xuat-update',
  templateUrl: './pro-ke-hoach-san-xuat-update.component.html'
})
export class ProKeHoachSanXuatUpdateComponent implements OnInit {
  isSaving: boolean;

  prochuyens: IProChuyen[];

  prosanphams: IProSanPham[];

  editForm = this.fb.group({
    id: [],
    ngaySanXuat: [],
    soLuongYeuCau: [],
    tinhTrang: [],
    ghiChu: [],
    sam: [],
    lotNo: [],
    proChuyenId: [],
    proSanPhamId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected proKeHoachSanXuatService: ProKeHoachSanXuatService,
    protected proChuyenService: ProChuyenService,
    protected proSanPhamService: ProSanPhamService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proKeHoachSanXuat }) => {
      this.updateForm(proKeHoachSanXuat);
    });
    this.proChuyenService
      .query()
      .subscribe((res: HttpResponse<IProChuyen[]>) => (this.prochuyens = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.proSanPhamService
      .query()
      .subscribe(
        (res: HttpResponse<IProSanPham[]>) => (this.prosanphams = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(proKeHoachSanXuat: IProKeHoachSanXuat) {
    this.editForm.patchValue({
      id: proKeHoachSanXuat.id,
      ngaySanXuat: proKeHoachSanXuat.ngaySanXuat != null ? proKeHoachSanXuat.ngaySanXuat.format(DATE_TIME_FORMAT) : null,
      soLuongYeuCau: proKeHoachSanXuat.soLuongYeuCau,
      tinhTrang: proKeHoachSanXuat.tinhTrang,
      ghiChu: proKeHoachSanXuat.ghiChu,
      sam: proKeHoachSanXuat.sam,
      lotNo: proKeHoachSanXuat.lotNo,
      proChuyenId: proKeHoachSanXuat.proChuyenId,
      proSanPhamId: proKeHoachSanXuat.proSanPhamId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proKeHoachSanXuat = this.createFromForm();
    if (proKeHoachSanXuat.id !== undefined) {
      this.subscribeToSaveResponse(this.proKeHoachSanXuatService.update(proKeHoachSanXuat));
    } else {
      this.subscribeToSaveResponse(this.proKeHoachSanXuatService.create(proKeHoachSanXuat));
    }
  }

  private createFromForm(): IProKeHoachSanXuat {
    return {
      ...new ProKeHoachSanXuat(),
      id: this.editForm.get(['id']).value,
      ngaySanXuat:
        this.editForm.get(['ngaySanXuat']).value != null ? moment(this.editForm.get(['ngaySanXuat']).value, DATE_TIME_FORMAT) : undefined,
      soLuongYeuCau: this.editForm.get(['soLuongYeuCau']).value,
      tinhTrang: this.editForm.get(['tinhTrang']).value,
      ghiChu: this.editForm.get(['ghiChu']).value,
      sam: this.editForm.get(['sam']).value,
      lotNo: this.editForm.get(['lotNo']).value,
      proChuyenId: this.editForm.get(['proChuyenId']).value,
      proSanPhamId: this.editForm.get(['proSanPhamId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProKeHoachSanXuat>>) {
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

  trackProSanPhamById(index: number, item: IProSanPham) {
    return item.id;
  }
}
