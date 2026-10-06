import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IProChungLoaiSanPham, ProChungLoaiSanPham } from 'app/shared/model/pro-chung-loai-san-pham.model';
import { ProChungLoaiSanPhamService } from './pro-chung-loai-san-pham.service';

@Component({
  selector: 'jhi-pro-chung-loai-san-pham-update',
  templateUrl: './pro-chung-loai-san-pham-update.component.html'
})
export class ProChungLoaiSanPhamUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    tenChungLoai: []
  });

  constructor(
    protected proChungLoaiSanPhamService: ProChungLoaiSanPhamService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ proChungLoaiSanPham }) => {
      this.updateForm(proChungLoaiSanPham);
    });
  }

  updateForm(proChungLoaiSanPham: IProChungLoaiSanPham) {
    this.editForm.patchValue({
      id: proChungLoaiSanPham.id,
      tenChungLoai: proChungLoaiSanPham.tenChungLoai
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const proChungLoaiSanPham = this.createFromForm();
    if (proChungLoaiSanPham.id !== undefined) {
      this.subscribeToSaveResponse(this.proChungLoaiSanPhamService.update(proChungLoaiSanPham));
    } else {
      this.subscribeToSaveResponse(this.proChungLoaiSanPhamService.create(proChungLoaiSanPham));
    }
  }

  private createFromForm(): IProChungLoaiSanPham {
    return {
      ...new ProChungLoaiSanPham(),
      id: this.editForm.get(['id']).value,
      tenChungLoai: this.editForm.get(['tenChungLoai']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProChungLoaiSanPham>>) {
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
