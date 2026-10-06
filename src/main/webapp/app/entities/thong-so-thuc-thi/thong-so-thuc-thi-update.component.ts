import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IThongSoThucThi, ThongSoThucThi } from 'app/shared/model/thong-so-thuc-thi.model';
import { ThongSoThucThiService } from './thong-so-thuc-thi.service';
import { IThongSoDoSPSize } from 'app/shared/model/thong-so-do-sp-size.model';
import { ThongSoDoSPSizeService } from 'app/entities/thong-so-do-sp-size/thong-so-do-sp-size.service';
import { IDungSai } from 'app/shared/model/dung-sai.model';
import { DungSaiService } from 'app/entities/dung-sai/dung-sai.service';
import { IProKeHoachSanXuat } from 'app/shared/model/pro-ke-hoach-san-xuat.model';
import { ProKeHoachSanXuatService } from 'app/entities/pro-ke-hoach-san-xuat/pro-ke-hoach-san-xuat.service';

@Component({
  selector: 'jhi-thong-so-thuc-thi-update',
  templateUrl: './thong-so-thuc-thi-update.component.html'
})
export class ThongSoThucThiUpdateComponent implements OnInit {
  isSaving: boolean;

  thongsodospsizes: IThongSoDoSPSize[];

  dungsais: IDungSai[];

  prokehoachsanxuats: IProKeHoachSanXuat[];

  editForm = this.fb.group({
    id: [],
    spIndex: [],
    thongSoDoSPSizeId: [],
    dungSaiId: [],
    proKeHoachSanXuatId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected thongSoThucThiService: ThongSoThucThiService,
    protected thongSoDoSPSizeService: ThongSoDoSPSizeService,
    protected dungSaiService: DungSaiService,
    protected proKeHoachSanXuatService: ProKeHoachSanXuatService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ thongSoThucThi }) => {
      this.updateForm(thongSoThucThi);
    });
    this.thongSoDoSPSizeService
      .query()
      .subscribe(
        (res: HttpResponse<IThongSoDoSPSize[]>) => (this.thongsodospsizes = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    this.dungSaiService
      .query()
      .subscribe((res: HttpResponse<IDungSai[]>) => (this.dungsais = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.proKeHoachSanXuatService
      .query()
      .subscribe(
        (res: HttpResponse<IProKeHoachSanXuat[]>) => (this.prokehoachsanxuats = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(thongSoThucThi: IThongSoThucThi) {
    this.editForm.patchValue({
      id: thongSoThucThi.id,
      spIndex: thongSoThucThi.spIndex,
      thongSoDoSPSizeId: thongSoThucThi.thongSoDoSPSizeId,
      dungSaiId: thongSoThucThi.dungSaiId,
      proKeHoachSanXuatId: thongSoThucThi.proKeHoachSanXuatId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const thongSoThucThi = this.createFromForm();
    if (thongSoThucThi.id !== undefined) {
      this.subscribeToSaveResponse(this.thongSoThucThiService.update(thongSoThucThi));
    } else {
      this.subscribeToSaveResponse(this.thongSoThucThiService.create(thongSoThucThi));
    }
  }

  private createFromForm(): IThongSoThucThi {
    return {
      ...new ThongSoThucThi(),
      id: this.editForm.get(['id']).value,
      spIndex: this.editForm.get(['spIndex']).value,
      thongSoDoSPSizeId: this.editForm.get(['thongSoDoSPSizeId']).value,
      dungSaiId: this.editForm.get(['dungSaiId']).value,
      proKeHoachSanXuatId: this.editForm.get(['proKeHoachSanXuatId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IThongSoThucThi>>) {
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

  trackThongSoDoSPSizeById(index: number, item: IThongSoDoSPSize) {
    return item.id;
  }

  trackDungSaiById(index: number, item: IDungSai) {
    return item.id;
  }

  trackProKeHoachSanXuatById(index: number, item: IProKeHoachSanXuat) {
    return item.id;
  }
}
