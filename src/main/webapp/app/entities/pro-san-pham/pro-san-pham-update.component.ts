import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IProSanPham, ProSanPham } from 'app/shared/model/pro-san-pham.model';
import { ProSanPhamService } from './pro-san-pham.service';
import { IProKhachHang } from 'app/shared/model/pro-khach-hang.model';
import { ProKhachHangService } from 'app/entities/pro-khach-hang/pro-khach-hang.service';
import { IProChungLoaiSanPham } from 'app/shared/model/pro-chung-loai-san-pham.model';
import { ProChungLoaiSanPhamService } from 'app/entities/pro-chung-loai-san-pham/pro-chung-loai-san-pham.service';
import { IProTenMua } from 'app/shared/model/pro-ten-mua.model';
import { ProTenMuaService } from 'app/entities/pro-ten-mua/pro-ten-mua.service';

@Component({
  selector: 'jhi-pro-san-pham-update',
  templateUrl: './pro-san-pham-update.component.html'
})
export class ProSanPhamUpdateComponent implements OnInit {
  isSaving: boolean;

  prokhachhangs: IProKhachHang[];

  prochungloaisanphams: IProChungLoaiSanPham[];

  protenmuas: IProTenMua[];

  editForm = this.fb.group({
    id: [],
    maSanPham: [],
    maDonHang: [],
    po: [],
    soLuongSanXuat: [],
    thongSoValue1: [],
    thongSoCaption1: [],
    thongSoCheck1: [],
    thongSoValue2: [],
    thongSoCaption2: [],
    thongSoCheck2: [],
    thongSoValue3: [],
    thongSoCaption3: [],
    thongSoCheck3: [],
    thongSoValue4: [],
    thongSoCaption4: [],
    thongSoCheck4: [],
    thongSoValue5: [],
    thongSoCaption5: [],
    thongSoCheck5: [],
    thongSoValue6: [],
    thongSoCaption6: [],
    thongSoCheck6: [],
    thongSoValue7: [],
    thongSoCaption7: [],
    thongSoCheck7: [],
    thongSoValue8: [],
    thongSoCaption8: [],
    thongSoCheck8: [],
    thongSoValue9: [],
    thongSoCaption9: [],
    thongSoCheck9: [],
    thongSoValue10: [],
    thongSoCaption10: [],
    thongSoCheck10: [],
    color: [],
    hinhMatTruocURL: [],
    hinhMatSauURL: [],
    proKhachHangId: [],
    proChungLoaiSanPhamId: [],
    proTenMuaId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected proSanPhamService: ProSanPhamService,
    protected proKhachHangService: ProKhachHangService,
    protected proChungLoaiSanPhamService: ProChungLoaiSanPhamService,
    protected proTenMuaService: ProTenMuaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proSanPham }) => {
      this.updateForm(proSanPham);
    });
    this.proKhachHangService
      .query()
      .subscribe(
        (res: HttpResponse<IProKhachHang[]>) => (this.prokhachhangs = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.proChungLoaiSanPhamService
      .query()
      .subscribe(
        (res: HttpResponse<IProChungLoaiSanPham[]>) => (this.prochungloaisanphams = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.proTenMuaService
      .query()
      .subscribe((res: HttpResponse<IProTenMua[]>) => (this.protenmuas = res.body), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(proSanPham: IProSanPham) {
    this.editForm.patchValue({
      id: proSanPham.id,
      maSanPham: proSanPham.maSanPham,
      maDonHang: proSanPham.maDonHang,
      po: proSanPham.po,
      soLuongSanXuat: proSanPham.soLuongSanXuat,
      thongSoValue1: proSanPham.thongSoValue1,
      thongSoCaption1: proSanPham.thongSoCaption1,
      thongSoCheck1: proSanPham.thongSoCheck1,
      thongSoValue2: proSanPham.thongSoValue2,
      thongSoCaption2: proSanPham.thongSoCaption2,
      thongSoCheck2: proSanPham.thongSoCheck2,
      thongSoValue3: proSanPham.thongSoValue3,
      thongSoCaption3: proSanPham.thongSoCaption3,
      thongSoCheck3: proSanPham.thongSoCheck3,
      thongSoValue4: proSanPham.thongSoValue4,
      thongSoCaption4: proSanPham.thongSoCaption4,
      thongSoCheck4: proSanPham.thongSoCheck4,
      thongSoValue5: proSanPham.thongSoValue5,
      thongSoCaption5: proSanPham.thongSoCaption5,
      thongSoCheck5: proSanPham.thongSoCheck5,
      thongSoValue6: proSanPham.thongSoValue6,
      thongSoCaption6: proSanPham.thongSoCaption6,
      thongSoCheck6: proSanPham.thongSoCheck6,
      thongSoValue7: proSanPham.thongSoValue7,
      thongSoCaption7: proSanPham.thongSoCaption7,
      thongSoCheck7: proSanPham.thongSoCheck7,
      thongSoValue8: proSanPham.thongSoValue8,
      thongSoCaption8: proSanPham.thongSoCaption8,
      thongSoCheck8: proSanPham.thongSoCheck8,
      thongSoValue9: proSanPham.thongSoValue9,
      thongSoCaption9: proSanPham.thongSoCaption9,
      thongSoCheck9: proSanPham.thongSoCheck9,
      thongSoValue10: proSanPham.thongSoValue10,
      thongSoCaption10: proSanPham.thongSoCaption10,
      thongSoCheck10: proSanPham.thongSoCheck10,
      color: proSanPham.color,
      hinhMatTruocURL: proSanPham.hinhMatTruocURL,
      hinhMatSauURL: proSanPham.hinhMatSauURL,
      proKhachHangId: proSanPham.proKhachHangId,
      proChungLoaiSanPhamId: proSanPham.proChungLoaiSanPhamId,
      proTenMuaId: proSanPham.proTenMuaId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proSanPham = this.createFromForm();
    if (proSanPham.id !== undefined) {
      this.subscribeToSaveResponse(this.proSanPhamService.update(proSanPham));
    } else {
      this.subscribeToSaveResponse(this.proSanPhamService.create(proSanPham));
    }
  }

  private createFromForm(): IProSanPham {
    return {
      ...new ProSanPham(),
      id: this.editForm.get(['id']).value,
      maSanPham: this.editForm.get(['maSanPham']).value,
      maDonHang: this.editForm.get(['maDonHang']).value,
      po: this.editForm.get(['po']).value,
      soLuongSanXuat: this.editForm.get(['soLuongSanXuat']).value,
      thongSoValue1: this.editForm.get(['thongSoValue1']).value,
      thongSoCaption1: this.editForm.get(['thongSoCaption1']).value,
      thongSoCheck1: this.editForm.get(['thongSoCheck1']).value,
      thongSoValue2: this.editForm.get(['thongSoValue2']).value,
      thongSoCaption2: this.editForm.get(['thongSoCaption2']).value,
      thongSoCheck2: this.editForm.get(['thongSoCheck2']).value,
      thongSoValue3: this.editForm.get(['thongSoValue3']).value,
      thongSoCaption3: this.editForm.get(['thongSoCaption3']).value,
      thongSoCheck3: this.editForm.get(['thongSoCheck3']).value,
      thongSoValue4: this.editForm.get(['thongSoValue4']).value,
      thongSoCaption4: this.editForm.get(['thongSoCaption4']).value,
      thongSoCheck4: this.editForm.get(['thongSoCheck4']).value,
      thongSoValue5: this.editForm.get(['thongSoValue5']).value,
      thongSoCaption5: this.editForm.get(['thongSoCaption5']).value,
      thongSoCheck5: this.editForm.get(['thongSoCheck5']).value,
      thongSoValue6: this.editForm.get(['thongSoValue6']).value,
      thongSoCaption6: this.editForm.get(['thongSoCaption6']).value,
      thongSoCheck6: this.editForm.get(['thongSoCheck6']).value,
      thongSoValue7: this.editForm.get(['thongSoValue7']).value,
      thongSoCaption7: this.editForm.get(['thongSoCaption7']).value,
      thongSoCheck7: this.editForm.get(['thongSoCheck7']).value,
      thongSoValue8: this.editForm.get(['thongSoValue8']).value,
      thongSoCaption8: this.editForm.get(['thongSoCaption8']).value,
      thongSoCheck8: this.editForm.get(['thongSoCheck8']).value,
      thongSoValue9: this.editForm.get(['thongSoValue9']).value,
      thongSoCaption9: this.editForm.get(['thongSoCaption9']).value,
      thongSoCheck9: this.editForm.get(['thongSoCheck9']).value,
      thongSoValue10: this.editForm.get(['thongSoValue10']).value,
      thongSoCaption10: this.editForm.get(['thongSoCaption10']).value,
      thongSoCheck10: this.editForm.get(['thongSoCheck10']).value,
      color: this.editForm.get(['color']).value,
      hinhMatTruocURL: this.editForm.get(['hinhMatTruocURL']).value,
      hinhMatSauURL: this.editForm.get(['hinhMatSauURL']).value,
      proKhachHangId: this.editForm.get(['proKhachHangId']).value,
      proChungLoaiSanPhamId: this.editForm.get(['proChungLoaiSanPhamId']).value,
      proTenMuaId: this.editForm.get(['proTenMuaId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProSanPham>>) {
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

  trackProKhachHangById(index: number, item: IProKhachHang) {
    return item.id;
  }

  trackProChungLoaiSanPhamById(index: number, item: IProChungLoaiSanPham) {
    return item.id;
  }

  trackProTenMuaById(index: number, item: IProTenMua) {
    return item.id;
  }
}
