import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IThongSoDoSP, ThongSoDoSP } from 'app/shared/model/thong-so-do-sp.model';
import { ThongSoDoSPService } from './thong-so-do-sp.service';
import { IThongSoDo } from 'app/shared/model/thong-so-do.model';
import { ThongSoDoService } from 'app/entities/thong-so-do/thong-so-do.service';
import { IDungSai } from 'app/shared/model/dung-sai.model';
import { DungSaiService } from 'app/entities/dung-sai/dung-sai.service';
import { IProSanPham } from 'app/shared/model/pro-san-pham.model';
import { ProSanPhamService } from 'app/entities/pro-san-pham/pro-san-pham.service';

@Component({
  selector: 'jhi-thong-so-do-sp-update',
  templateUrl: './thong-so-do-sp-update.component.html'
})
export class ThongSoDoSPUpdateComponent implements OnInit {
  isSaving: boolean;

  thongsodos: IThongSoDo[];

  dungsais: IDungSai[];

  prosanphams: IProSanPham[];

  editForm = this.fb.group({
    id: [],
    note: [],
    thongSoDoId: [],
    dungSaiId: [],
    proSanPhamId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected thongSoDoSPService: ThongSoDoSPService,
    protected thongSoDoService: ThongSoDoService,
    protected dungSaiService: DungSaiService,
    protected proSanPhamService: ProSanPhamService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ thongSoDoSP }) => {
      this.updateForm(thongSoDoSP);
    });
    this.thongSoDoService
      .query()
      .subscribe((res: HttpResponse<IThongSoDo[]>) => (this.thongsodos = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.dungSaiService
      .query()
      .subscribe((res: HttpResponse<IDungSai[]>) => (this.dungsais = res.body), (res: HttpErrorResponse) => this.onError(res.message));
    this.proSanPhamService
      .query()
      .subscribe(
        (res: HttpResponse<IProSanPham[]>) => (this.prosanphams = res.body),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  updateForm(thongSoDoSP: IThongSoDoSP) {
    this.editForm.patchValue({
      id: thongSoDoSP.id,
      note: thongSoDoSP.note,
      thongSoDoId: thongSoDoSP.thongSoDoId,
      dungSaiId: thongSoDoSP.dungSaiId,
      proSanPhamId: thongSoDoSP.proSanPhamId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const thongSoDoSP = this.createFromForm();
    if (thongSoDoSP.id !== undefined) {
      this.subscribeToSaveResponse(this.thongSoDoSPService.update(thongSoDoSP));
    } else {
      this.subscribeToSaveResponse(this.thongSoDoSPService.create(thongSoDoSP));
    }
  }

  private createFromForm(): IThongSoDoSP {
    return {
      ...new ThongSoDoSP(),
      id: this.editForm.get(['id']).value,
      note: this.editForm.get(['note']).value,
      thongSoDoId: this.editForm.get(['thongSoDoId']).value,
      dungSaiId: this.editForm.get(['dungSaiId']).value,
      proSanPhamId: this.editForm.get(['proSanPhamId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IThongSoDoSP>>) {
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

  trackThongSoDoById(index: number, item: IThongSoDo) {
    return item.id;
  }

  trackDungSaiById(index: number, item: IDungSai) {
    return item.id;
  }

  trackProSanPhamById(index: number, item: IProSanPham) {
    return item.id;
  }
}
