import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IThongSoDoSPSize, ThongSoDoSPSize } from 'app/shared/model/thong-so-do-sp-size.model';
import { ThongSoDoSPSizeService } from './thong-so-do-sp-size.service';
import { IThongSoDoSP } from 'app/shared/model/thong-so-do-sp.model';
import { ThongSoDoSPService } from 'app/entities/thong-so-do-sp/thong-so-do-sp.service';

@Component({
  selector: 'jhi-thong-so-do-sp-size-update',
  templateUrl: './thong-so-do-sp-size-update.component.html'
})
export class ThongSoDoSPSizeUpdateComponent implements OnInit {
  isSaving: boolean;

  thongsodosps: IThongSoDoSP[];

  editForm = this.fb.group({
    id: [],
    size: [],
    cmThongSoChuan: [],
    inchThongSoChuan: [],
    cmThongSoChuanValue: [],
    inchThongSoChuanValue: [],
    thongSoDoSPId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected thongSoDoSPSizeService: ThongSoDoSPSizeService,
    protected thongSoDoSPService: ThongSoDoSPService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ thongSoDoSPSize }) => {
      this.updateForm(thongSoDoSPSize);
    });
    this.thongSoDoSPService
      .query()
      .subscribe(
        (res: HttpResponse<IThongSoDoSP[]>) => (this.thongsodosps = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(thongSoDoSPSize: IThongSoDoSPSize) {
    this.editForm.patchValue({
      id: thongSoDoSPSize.id,
      size: thongSoDoSPSize.size,
      cmThongSoChuan: thongSoDoSPSize.cmThongSoChuan,
      inchThongSoChuan: thongSoDoSPSize.inchThongSoChuan,
      cmThongSoChuanValue: thongSoDoSPSize.cmThongSoChuanValue,
      inchThongSoChuanValue: thongSoDoSPSize.inchThongSoChuanValue,
      thongSoDoSPId: thongSoDoSPSize.thongSoDoSPId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const thongSoDoSPSize = this.createFromForm();
    if (thongSoDoSPSize.id !== undefined) {
      this.subscribeToSaveResponse(this.thongSoDoSPSizeService.update(thongSoDoSPSize));
    } else {
      this.subscribeToSaveResponse(this.thongSoDoSPSizeService.create(thongSoDoSPSize));
    }
  }

  private createFromForm(): IThongSoDoSPSize {
    return {
      ...new ThongSoDoSPSize(),
      id: this.editForm.get(['id']).value,
      size: this.editForm.get(['size']).value,
      cmThongSoChuan: this.editForm.get(['cmThongSoChuan']).value,
      inchThongSoChuan: this.editForm.get(['inchThongSoChuan']).value,
      cmThongSoChuanValue: this.editForm.get(['cmThongSoChuanValue']).value,
      inchThongSoChuanValue: this.editForm.get(['inchThongSoChuanValue']).value,
      thongSoDoSPId: this.editForm.get(['thongSoDoSPId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IThongSoDoSPSize>>) {
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

  trackThongSoDoSPById(index: number, item: IThongSoDoSP) {
    return item.id;
  }
}
