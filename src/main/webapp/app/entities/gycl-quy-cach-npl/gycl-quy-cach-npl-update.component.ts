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
import { IGYCLQuyCachNPL, GYCLQuyCachNPL } from 'app/shared/model/gycl-quy-cach-npl.model';
import { GYCLQuyCachNPLService } from './gycl-quy-cach-npl.service';
import { IGopYChatLuong } from 'app/shared/model/gop-y-chat-luong.model';
import { GopYChatLuongService } from 'app/entities/gop-y-chat-luong/gop-y-chat-luong.service';

@Component({
  selector: 'jhi-gycl-quy-cach-npl-update',
  templateUrl: './gycl-quy-cach-npl-update.component.html'
})
export class GYCLQuyCachNPLUpdateComponent implements OnInit {
  isSaving: boolean;

  gopychatluongs: IGopYChatLuong[];

  editForm = this.fb.group({
    id: [],
    tenNPL: [],
    nguoiTao: [],
    ngayTao: [],
    userId: [],
    yesNo: [],
    nA: [],
    proGopYChatLuongId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected gYCLQuyCachNPLService: GYCLQuyCachNPLService,
    protected gopYChatLuongService: GopYChatLuongService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ gYCLQuyCachNPL }) => {
      this.updateForm(gYCLQuyCachNPL);
    });
    this.gopYChatLuongService
      .query()
      .subscribe(
        (res: HttpResponse<IGopYChatLuong[]>) => (this.gopychatluongs = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(gYCLQuyCachNPL: IGYCLQuyCachNPL) {
    this.editForm.patchValue({
      id: gYCLQuyCachNPL.id,
      tenNPL: gYCLQuyCachNPL.tenNPL,
      nguoiTao: gYCLQuyCachNPL.nguoiTao,
      ngayTao: gYCLQuyCachNPL.ngayTao != null ? gYCLQuyCachNPL.ngayTao.format(DATE_TIME_FORMAT) : null,
      userId: gYCLQuyCachNPL.userId,
      yesNo: gYCLQuyCachNPL.yesNo,
      nA: gYCLQuyCachNPL.nA,
      proGopYChatLuongId: gYCLQuyCachNPL.proGopYChatLuongId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const gYCLQuyCachNPL = this.createFromForm();
    if (gYCLQuyCachNPL.id !== undefined) {
      this.subscribeToSaveResponse(this.gYCLQuyCachNPLService.update(gYCLQuyCachNPL));
    } else {
      this.subscribeToSaveResponse(this.gYCLQuyCachNPLService.create(gYCLQuyCachNPL));
    }
  }

  private createFromForm(): IGYCLQuyCachNPL {
    return {
      ...new GYCLQuyCachNPL(),
      id: this.editForm.get(['id']).value,
      tenNPL: this.editForm.get(['tenNPL']).value,
      nguoiTao: this.editForm.get(['nguoiTao']).value,
      ngayTao: this.editForm.get(['ngayTao']).value != null ? moment(this.editForm.get(['ngayTao']).value, DATE_TIME_FORMAT) : undefined,
      userId: this.editForm.get(['userId']).value,
      yesNo: this.editForm.get(['yesNo']).value,
      nA: this.editForm.get(['nA']).value,
      proGopYChatLuongId: this.editForm.get(['proGopYChatLuongId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGYCLQuyCachNPL>>) {
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
}
