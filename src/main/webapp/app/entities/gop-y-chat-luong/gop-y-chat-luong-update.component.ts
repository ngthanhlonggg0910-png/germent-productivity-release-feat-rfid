import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IGopYChatLuong, GopYChatLuong } from 'app/shared/model/gop-y-chat-luong.model';
import { GopYChatLuongService } from './gop-y-chat-luong.service';

@Component({
  selector: 'jhi-gop-y-chat-luong-update',
  templateUrl: './gop-y-chat-luong-update.component.html'
})
export class GopYChatLuongUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    cusId: [],
    cusName: [],
    chuyenId: [],
    chuyenName: [],
    maHang: [],
    po: [],
    poId: [],
    color: [],
    size: [],
    firstCut: [],
    inline: [],
    lanBanHanh: [],
    ngayBaoCao: [],
    nguoiTao: [],
    userId: [],
    ngayTao: [],
    tenQuanLyXuong: [],
    tenKiThuat: [],
    tenQanLyCat: [],
    tenQA: []
  });

  constructor(protected gopYChatLuongService: GopYChatLuongService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ gopYChatLuong }) => {
      this.updateForm(gopYChatLuong);
    });
  }

  updateForm(gopYChatLuong: IGopYChatLuong) {
    this.editForm.patchValue({
      id: gopYChatLuong.id,
      cusId: gopYChatLuong.cusId,
      cusName: gopYChatLuong.cusName,
      chuyenId: gopYChatLuong.chuyenId,
      chuyenName: gopYChatLuong.chuyenName,
      maHang: gopYChatLuong.maHang,
      po: gopYChatLuong.po,
      poId: gopYChatLuong.poId,
      color: gopYChatLuong.color,
      size: gopYChatLuong.size,
      firstCut: gopYChatLuong.firstCut,
      inline: gopYChatLuong.inline,
      lanBanHanh: gopYChatLuong.lanBanHanh,
      ngayBaoCao: gopYChatLuong.ngayBaoCao != null ? gopYChatLuong.ngayBaoCao.format(DATE_TIME_FORMAT) : null,
      nguoiTao: gopYChatLuong.nguoiTao,
      userId: gopYChatLuong.userId != null ? gopYChatLuong.userId.format(DATE_TIME_FORMAT) : null,
      ngayTao: gopYChatLuong.ngayTao != null ? gopYChatLuong.ngayTao.format(DATE_TIME_FORMAT) : null,
      tenQuanLyXuong: gopYChatLuong.tenQuanLyXuong,
      tenKiThuat: gopYChatLuong.tenKiThuat,
      tenQanLyCat: gopYChatLuong.tenQanLyCat,
      tenQA: gopYChatLuong.tenQA
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const gopYChatLuong = this.createFromForm();
    if (gopYChatLuong.id !== undefined) {
      this.subscribeToSaveResponse(this.gopYChatLuongService.update(gopYChatLuong));
    } else {
      this.subscribeToSaveResponse(this.gopYChatLuongService.create(gopYChatLuong));
    }
  }

  private createFromForm(): IGopYChatLuong {
    return {
      ...new GopYChatLuong(),
      id: this.editForm.get(['id']).value,
      cusId: this.editForm.get(['cusId']).value,
      cusName: this.editForm.get(['cusName']).value,
      chuyenId: this.editForm.get(['chuyenId']).value,
      chuyenName: this.editForm.get(['chuyenName']).value,
      maHang: this.editForm.get(['maHang']).value,
      po: this.editForm.get(['po']).value,
      poId: this.editForm.get(['poId']).value,
      color: this.editForm.get(['color']).value,
      size: this.editForm.get(['size']).value,
      firstCut: this.editForm.get(['firstCut']).value,
      inline: this.editForm.get(['inline']).value,
      lanBanHanh: this.editForm.get(['lanBanHanh']).value,
      ngayBaoCao:
        this.editForm.get(['ngayBaoCao']).value != null ? moment(this.editForm.get(['ngayBaoCao']).value, DATE_TIME_FORMAT) : undefined,
      nguoiTao: this.editForm.get(['nguoiTao']).value,
      userId: this.editForm.get(['userId']).value != null ? moment(this.editForm.get(['userId']).value, DATE_TIME_FORMAT) : undefined,
      ngayTao: this.editForm.get(['ngayTao']).value != null ? moment(this.editForm.get(['ngayTao']).value, DATE_TIME_FORMAT) : undefined,
      tenQuanLyXuong: this.editForm.get(['tenQuanLyXuong']).value,
      tenKiThuat: this.editForm.get(['tenKiThuat']).value,
      tenQanLyCat: this.editForm.get(['tenQanLyCat']).value,
      tenQA: this.editForm.get(['tenQA']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGopYChatLuong>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
