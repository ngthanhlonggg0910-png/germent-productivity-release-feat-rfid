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
import { IGYCLKhacPhuc, GYCLKhacPhuc } from 'app/shared/model/gycl-khac-phuc.model';
import { GYCLKhacPhucService } from './gycl-khac-phuc.service';
import { IGopYChatLuong } from 'app/shared/model/gop-y-chat-luong.model';
import { GopYChatLuongService } from 'app/entities/gop-y-chat-luong/gop-y-chat-luong.service';
import { IThuVienLoi } from 'app/shared/model/thu-vien-loi.model';
import { ThuVienLoiService } from 'app/entities/thu-vien-loi/thu-vien-loi.service';
import { IKhacPhucLoi } from 'app/shared/model/khac-phuc-loi.model';
import { KhacPhucLoiService } from 'app/entities/khac-phuc-loi/khac-phuc-loi.service';

@Component({
  selector: 'jhi-gycl-khac-phuc-update',
  templateUrl: './gycl-khac-phuc-update.component.html'
})
export class GYCLKhacPhucUpdateComponent implements OnInit {
  isSaving: boolean;

  gopychatluongs: IGopYChatLuong[];

  thuvienlois: IThuVienLoi[];

  khacphuclois: IKhacPhucLoi[];

  editForm = this.fb.group({
    id: [],
    cumId: [],
    tenCum: [],
    gopY: [],
    ketQua: [],
    nguoiTao: [],
    userId: [],
    ngayTao: [],
    proGopYChatLuongId: [],
    thuVienLoiId: [],
    khacPhucLoiId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected gYCLKhacPhucService: GYCLKhacPhucService,
    protected gopYChatLuongService: GopYChatLuongService,
    protected thuVienLoiService: ThuVienLoiService,
    protected khacPhucLoiService: KhacPhucLoiService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ gYCLKhacPhuc }) => {
      this.updateForm(gYCLKhacPhuc);
    });
    this.gopYChatLuongService
      .query()
      .subscribe(
        (res: HttpResponse<IGopYChatLuong[]>) => (this.gopychatluongs = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.thuVienLoiService
      .query()
      .subscribe(
        (res: HttpResponse<IThuVienLoi[]>) => (this.thuvienlois = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.khacPhucLoiService
      .query()
      .subscribe(
        (res: HttpResponse<IKhacPhucLoi[]>) => (this.khacphuclois = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(gYCLKhacPhuc: IGYCLKhacPhuc) {
    this.editForm.patchValue({
      id: gYCLKhacPhuc.id,
      cumId: gYCLKhacPhuc.cumId,
      tenCum: gYCLKhacPhuc.tenCum,
      gopY: gYCLKhacPhuc.gopY,
      ketQua: gYCLKhacPhuc.ketQua,
      nguoiTao: gYCLKhacPhuc.nguoiTao,
      userId: gYCLKhacPhuc.userId != null ? gYCLKhacPhuc.userId.format(DATE_TIME_FORMAT) : null,
      ngayTao: gYCLKhacPhuc.ngayTao != null ? gYCLKhacPhuc.ngayTao.format(DATE_TIME_FORMAT) : null,
      proGopYChatLuongId: gYCLKhacPhuc.proGopYChatLuongId,
      thuVienLoiId: gYCLKhacPhuc.thuVienLoiId,
      khacPhucLoiId: gYCLKhacPhuc.khacPhucLoiId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const gYCLKhacPhuc = this.createFromForm();
    if (gYCLKhacPhuc.id !== undefined) {
      this.subscribeToSaveResponse(this.gYCLKhacPhucService.update(gYCLKhacPhuc));
    } else {
      this.subscribeToSaveResponse(this.gYCLKhacPhucService.create(gYCLKhacPhuc));
    }
  }

  private createFromForm(): IGYCLKhacPhuc {
    return {
      ...new GYCLKhacPhuc(),
      id: this.editForm.get(['id']).value,
      cumId: this.editForm.get(['cumId']).value,
      tenCum: this.editForm.get(['tenCum']).value,
      gopY: this.editForm.get(['gopY']).value,
      ketQua: this.editForm.get(['ketQua']).value,
      nguoiTao: this.editForm.get(['nguoiTao']).value,
      userId: this.editForm.get(['userId']).value != null ? moment(this.editForm.get(['userId']).value, DATE_TIME_FORMAT) : undefined,
      ngayTao: this.editForm.get(['ngayTao']).value != null ? moment(this.editForm.get(['ngayTao']).value, DATE_TIME_FORMAT) : undefined,
      proGopYChatLuongId: this.editForm.get(['proGopYChatLuongId']).value,
      thuVienLoiId: this.editForm.get(['thuVienLoiId']).value,
      khacPhucLoiId: this.editForm.get(['khacPhucLoiId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGYCLKhacPhuc>>) {
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

  trackGopYChatLuongById(index: number, item: IGopYChatLuong) {
    return item.id;
  }

  trackThuVienLoiById(index: number, item: IThuVienLoi) {
    return item.id;
  }

  trackKhacPhucLoiById(index: number, item: IKhacPhucLoi) {
    return item.id;
  }
}
